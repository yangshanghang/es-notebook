/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.Page;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.mapper.MyFavoriteMapper;
import com.icinfo.lpsp.notebook.note.mapper.NoteMapper;
import com.icinfo.lpsp.notebook.note.model.MyFavorite;
import com.icinfo.lpsp.notebook.note.service.IMyFavoriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 我的收藏表 NBOOK_MY_COLLECT 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
@Service
public class MyFavoriteServiceImpl extends MyBatisServiceSupport implements IMyFavoriteService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(MyFavoriteServiceImpl.class);

    /**
     * 注入我的收藏服务
     */
    @Autowired
    private MyFavoriteMapper myFavoriteMapper;

    /**
     * 注入笔记服务
     */
    @Autowired
    private NoteMapper noteMapper;


    /**
     * 描述：保存 我的收藏
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @return true:保存成功 false 保存失败
     * @throws Exception
     */
    @Override
    public boolean save(String userId, String noteId) throws Exception {
        //1.创建收藏
        MyFavorite myFavorite = new MyFavorite();
        //2.设置属性
        myFavorite.setUserId(userId);
        myFavorite.setNoteId(noteId);
        myFavorite.setCreateTime(new Date());
        myFavorite.setOperateTime(myFavorite.getCreateTime());
        //插入收藏
        myFavoriteMapper.insert(myFavorite);
        return true;
    }

    /**
     * 描述：删除 我的收藏
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @return true:删除成功 false 删除失败
     * @throws Exception
     */
    @Override
    public boolean remove(String userId, String noteId) throws Exception {
        Example example = new Example(MyFavorite.class);
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("noteId", noteId);
        return myFavoriteMapper.deleteByExample(example) > 0 ? true : false;
    }


    /**
     * 描述：获取我的收藏列表
     *
     * @param userId         用户id
     * @param categoryId     类别id
     * @param queryParamLike 查询参数
     * @param pageNum        页码
     * @param pageSize       页数
     * @return 我的收藏列表
     * @throws Exception
     */
    @Override
    public PageInfo<NoteDto> getMyFavoriteList(String userId, String categoryId, String queryParamLike, int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("categoryId", categoryId);
        map.put("queryParamLike", queryParamLike);
        Page<NoteDto> page = (Page<NoteDto>) noteMapper.getMyFavoriteNoteList(map);
        return page.toPageInfo();
    }

    /**
     * 描述：判断用户是否已收藏此笔记
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @return true： 笔记已收藏 false：笔记未收藏
     * @throws Exception
     */
    @Override
    public boolean isFavorite(String userId, String noteId) throws Exception {
        Example example = new Example(MyFavorite.class);
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("noteId", noteId);
        return myFavoriteMapper.selectByExample(example).size() > 0 ? true : false;
    }
}