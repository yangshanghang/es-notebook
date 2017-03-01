/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年11月22日
 * @version 2.0
 */
package com.icinfo.lpsp.notebook.note.mapper;

import com.icinfo.framework.mybatis.mapper.common.Mapper;
import com.icinfo.lpsp.notebook.note.dto.CommentDto;
import com.icinfo.lpsp.notebook.note.model.Comment;

import java.util.List;
import java.util.Map;

/**
 * 描述: 评论表 NBOOK_COMMENT 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public interface CommentMapper extends Mapper<Comment> {

    /**
     * 根据笔记id查询所有评论(按发表时间排序)
     *
     * @param map
     * @return
     * @throws Exception
     */
    List<CommentDto> selectCommentList(Map<String, Object> map) throws Exception;

}