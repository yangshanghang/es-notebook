/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.dto;

import com.icinfo.lpsp.notebook.note.model.Comment;

/**
 * 描述: 评论表 NBOOK_COMMENT 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public class CommentDto extends Comment {
    /**
     * 评论发表人
     */
    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}