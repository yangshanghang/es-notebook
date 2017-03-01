/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.pagehelper.Page;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.note.dto.CommentDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.mapper.CommentMapper;
import com.icinfo.lpsp.notebook.note.model.Comment;
import com.icinfo.lpsp.notebook.note.service.ICommentService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 评论表 NBOOK_COMMENT 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
@Service
public class CommentServiceImpl extends MyBatisServiceSupport implements ICommentService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * 评论Mapper
     */
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 笔记服务
     */
    @Autowired
    private INoteService noteService;

    @Autowired
    private JestClient jestClient;

    /**
     * 描述：保存评论内容
     *
     * @param userId  用户id
     * @param comment 评论内容
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String userId, Comment comment) throws Exception {
        // 1.数据库保存评论信息
        comment.setUserId(userId);
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);

        // 2.es 评论数加1
        NoteDto noteDto = noteService.get(comment.getNoteId());
        noteDto.setCommentCount(noteDto.getCommentCount() + 1);
        Index.Builder builder = new Index.Builder(noteDto).id(comment.getNoteId()).refresh(true);
        Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
        jestClient.execute(index);
    }

    /**
     * 描述：获取评论数量
     *
     * @param noteId 笔记id
     * @return 评论数量
     * @throws Exception
     */
    @Override
    public int getCommentCount(String noteId) throws Exception {
        Comment comment = new Comment();
        comment.setNoteId(noteId);
        return commentMapper.selectCount(comment);
    }

    /**
     * 描述：根据id获取评论信息
     *
     * @param id 评论id
     * @return 评论信息
     * @throws Exception
     */
    @Override
    public Comment getComment(String id) throws Exception {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 描述：获取所有评论（按发表时间排序）
     *
     * @param indexId  笔记id
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 评论列表
     * @throws Exception
     */
    @Override
    public PageInfo<CommentDto> getCommentList(String indexId, int pageNum, int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("noteId", indexId);
        PageHelper.startPage(pageNum, pageSize);
        Page<CommentDto> page = (Page<CommentDto>) commentMapper.selectCommentList(map);
        return page.toPageInfo();
    }

    /**
     * 描述：删除评论
     *
     * @param commentId 评论id
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeComment(String commentId) throws Exception {
        // 1.根据id查询数据库
        Comment dComment = commentMapper.selectByPrimaryKey(commentId);
        // 2.若查询到数据，则删除
        if (dComment != null) {
            commentMapper.delete(dComment);

            // 3.es 评论数减1
            NoteDto noteDto = noteService.get(dComment.getNoteId());
            noteDto.setCommentCount(noteDto.getCommentCount() - 1);
            Index.Builder builder = new Index.Builder(noteDto).id(dComment.getNoteId()).refresh(true);
            Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
            jestClient.execute(index);
        }
    }
}