/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.Page;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.*;
import com.icinfo.lpsp.notebook.note.mapper.CategoryMapper;
import com.icinfo.lpsp.notebook.note.model.Category;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:  NBOOK_CATEGORY 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月15日
 */
@Service
public class CategoryServiceImpl extends MyBatisServiceSupport implements ICategoryService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * 注入类目Mapper
     */
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private JestClient jestClient;

    /**
     * 描述：根据用户id 排序查找所有类目
     *
     * @param id 用户id
     * @return 类目列表
     * @throws Exception
     */
    @Override
    public List<CategoryDto> getCategoryList(String id) throws Exception {

        //1.获取用户获取类别页数据集合
        Map<String, List<CategoryDto>> categoryData = getCategoryData(id);
        //2.合并关注与其他类别
        List<CategoryDto> list = categoryData.get("focus");
        list.addAll(categoryData.get("other"));
        return list;
    }

    /**
     * 按照排序查找所有类目
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> getCategoryList() throws Exception {
        Example categoryExample = new Example(Category.class);
        categoryExample.setOrderByClause("NC_ORDER_NUMBER");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;
    }

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 类目
     * @throws Exception
     */
    @Override
    public Category get(String id) throws Exception {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 描述：插入或更新类目
     *
     * @param category 类目信息
     * @throws Exception
     */
    @Override
    public void saveOrModify(Category category) throws Exception {
        // 插入
        if (StringUtils.isBlank(category.getId())) {
            categoryMapper.insert(category);
        }
        // 更新
        else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /**
     * 描述：删除类目
     *
     * @param category 类目信息
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Category category) throws Exception {
        // 1.根据id查找数据库
        Category noteType = categoryMapper.selectByPrimaryKey(category.getId());
        if (noteType != null) {
            // 2.若查询到数据，则删除
            categoryMapper.delete(category);
        }
    }

    /**
     * 描述：获取类别页数据集合
     *
     * @param id 用户id
     * @return 我的关注类别和所有类别集合
     * @throws Exception
     */
    @Override
    public Map<String, List<CategoryDto>> getCategoryData(String id) throws Exception {
        // 1.获取用户关注列表
        List<CategoryDto> myFocuses = categoryMapper.selectList(id);

        // 2.获取所有的类目
        List<CategoryDto> categories = categoryMapper.selectAllList();

        // 3.获取ES中的类目及相应笔记数
        List<CategoryResultDto> categoryResultDtos = getESCategoryList();

        // 4.设置全部类目中的笔记数
        for (CategoryDto c : categories) {
            for (CategoryResultDto categoryResultDto : categoryResultDtos) {
                if (c.getId().equals(categoryResultDto.getKey())) {
                    c.setNoteCount(categoryResultDto.getDoc_count());
                    break;
                }
            }
        }

        // 5.设置关注类目中的笔记数，并剔除全部类目中已关注的
        for (CategoryDto myFocuse : myFocuses) {
            for (CategoryDto categoryAllDto : categories) {
                if (myFocuse.getId().equals(categoryAllDto.getId())) {
                    myFocuse.setNoteCount(categoryAllDto.getNoteCount());
                    categories.remove(categoryAllDto);
                    break;
                }
            }
        }

        // 6.组合map返回
        Map<String, List<CategoryDto>> map = new HashMap<>();
        map.put("focus", myFocuses);
        map.put("other", categories);
        return map;
    }

    /**
     * ES查询获取所有类目笔记数
     *
     * @return 类目笔记数
     * @throws Exception
     */
    private List<CategoryResultDto> getESCategoryList() throws Exception {
        // 1.聚合
        TermsBuilder gradeTermsBuilder = AggregationBuilders.terms("categoryAgg").field("category.id").size(0);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().aggregation(gradeTermsBuilder).size(0);
        // 2.查询
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(Constant.INDEX_NAME_NOTE)
                .addType(Constant.INDEX_TYPE_NOTE)
                .build();
        // 3.执行查询返回结果
        JestResult result = jestClient.execute(search);
        List<CategoryResultDto> categoryResultDtos = new ArrayList<>();
        // 4.执行成功，获取类目笔记数
        if (result.isSucceeded()) {
            String str = result.getJsonObject().get("aggregations").toString();
            ObjectMapper object = new ObjectMapper();
            ESAggDataDto aggDataDto = object.readValue(str, ESAggDataDto.class);
            categoryResultDtos = aggDataDto.getCategoryAgg().getBuckets();
        }
        return categoryResultDtos;
    }

    /**
     * 描述：获取初始化我的类别
     *
     * @param userId 用户id
     * @return 我的关注类别列表
     * @throws Exception
     */
    @Override
    public List<Category> getInitMyCategory(String userId) throws Exception {
        PageHelper.startPage(Constant.DEFAULT_PAGENUM, Constant.PAGESIZE_FIVE);
        Example categoryExample = new Example(Category.class);
        categoryExample.setOrderByClause("NC_ORDER_NUMBER");
        Page<Category> page = (Page<Category>) categoryMapper.selectByExample(categoryExample);
        return page.toPageInfo().getList();
    }
}