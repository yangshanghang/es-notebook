/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.lpsp.notebook.note.mapper.MyFocusMapper;
import com.icinfo.lpsp.notebook.note.model.Category;
import com.icinfo.lpsp.notebook.note.model.MyFocus;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IMyFocusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 描述: 我的关注表 NBOOK_MY_ATTENTION 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
@Service
public class MyFocusServiceImpl extends MyBatisServiceSupport implements IMyFocusService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(MyFocusServiceImpl.class);

    /**
     * 注入分类关注服务
     */
    @Autowired
    private MyFocusMapper myFocusMapper;

    /**
     * 注入类别服务
     */
    @Autowired
    private ICategoryService categoryService;


    /**
     * 描述：保存我的关注
     *
     * @param id          用户id
     * @param myFocusList 我的关注列表
     * @return 保存我的关注结果
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveList(String id, List<MyFocus> myFocusList) throws Exception {
        //1.删除我的关注
        Example example = new Example(MyFocus.class);
        example.createCriteria().andEqualTo("userId", id);
        myFocusMapper.deleteByExample(example);
        //2.插入我的关注
        for (MyFocus myFocus : myFocusList) {
            myFocus.setUserId(id);
            myFocus.setCreateTime(new Date());
            myFocus.setOperateTime(myFocus.getCreateTime());
            myFocusMapper.insert(myFocus);
        }
        return true;
    }

    /**
     * 描述：初始化我的关注
     *
     * @param userId 用户id
     * @throws Exception
     */
    @Override
    public void initMyFocus(String userId) throws Exception {
        //1.根据排序获取类别
        List<Category> categorys = categoryService.getInitMyCategory(userId);
        //2.保存初始化我的关注
        for (Category category : categorys) {
            MyFocus myfocus = new MyFocus();
            myfocus.setUserId(userId);
            myfocus.setCategoryId(category.getId());
            myfocus.setOrderNumber(category.getOrderNumber());
            myfocus.setCreateTime(new Date());
            myfocus.setOperateTime(myfocus.getCreateTime());
            myFocusMapper.insert(myfocus);
        }

    }

    /**
     * 描述：删除我的关注
     *
     * @param userId 用户id
     * @throws Exception
     */
    @Override
    public void deleteMyFocus(String userId) throws Exception {
        MyFocus myFocus = new MyFocus();
        myFocus.setUserId(userId);
        myFocusMapper.delete(myFocus);
    }
}