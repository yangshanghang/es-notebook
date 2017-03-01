/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.note.dto.CategoryDto;
import com.icinfo.lpsp.notebook.note.model.Category;

import java.util.List;
import java.util.Map;

/**
 * 描述:  NBOOK_CATEGORY 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月15日
 */
public interface ICategoryService extends BaseService {

    /**
     * 描述：根据用户id 排序查找所有类目
     *
     * @param id 用户id
     * @return 类目列表
     * @throws Exception
     */
    List<CategoryDto> getCategoryList(String id) throws Exception;

    /**
     * 描述：按照排序查找所有类目
     *
     * @return
     * @throws Exception
     */
    List<Category> getCategoryList() throws Exception;

    /**
     * 根据id查询类目
     *
     * @param id 类目id
     * @return 类目信息
     * @throws Exception
     */
    Category get(String id) throws Exception;

    /**
     * 描述：插入或更新类目
     *
     * @param category 类目信息
     * @throws Exception
     */
    void saveOrModify(Category category) throws Exception;

    /**
     * 删除类目
     *
     * @param category 类目信息
     * @throws Exception
     */
    void remove(Category category) throws Exception;

    /**
     * 描述：获取类别页数据集合
     *
     * @param id 用户id
     * @return 我的关注类别和所有类别集合
     * @throws Exception
     */
    Map<String, List<CategoryDto>> getCategoryData(String id) throws Exception;

    /**
     * 描述：获取初始化我的类别
     *
     * @param userId 用户id
     * @return 我的关注类别列表
     * @throws Exception
     */
    List<Category> getInitMyCategory(String userId) throws Exception;

}