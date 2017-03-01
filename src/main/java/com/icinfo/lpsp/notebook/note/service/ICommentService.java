/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.note.dto.CommentDto;
import com.icinfo.lpsp.notebook.note.model.Comment;

/**
 * 描述: 评论表 NBOOK_COMMENT 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public interface ICommentService extends BaseService {

    /**
     * 描述：保存评论内容
     *
     * @param userId  用户id
     * @param comment 评论内容
     * @throws Exception
     */
    void save(String userId, Comment comment) throws Exception;

    /**
     * 描述：获取所有评论（按发表时间排序）
     *
     * @param indexId  笔记id
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 评论列表
     * @throws Exception
     */
    PageInfo<CommentDto> getCommentList(String indexId, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：删除评论
     *
     * @param commentId 评论id
     * @throws Exception
     */
    void removeComment(String commentId) throws Exception;

    /**
     * 描述：获取评论数量
     *
     * @param noteId 笔记id
     * @return 评论数量
     * @throws Exception
     */
    int getCommentCount(String noteId) throws Exception;

    /**
     * 描述：根据id获取评论信息
     *
     * @param id 评论id
     * @return 评论信息
     * @throws Exception
     */
    Comment getComment(String id) throws Exception;
}