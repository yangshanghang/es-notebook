package com.icinfo.lpsp.notebook.note.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.lpsp.notebook.note.model.Category;
import com.icinfo.lpsp.notebook.note.model.Note;

import java.util.Date;

/**
 * 笔记Dto
 * Created by Administrator on 2016/11/16.
 */
public class NoteDto extends Note {
    /**
     * 描述：类别
     */
    private Category category;

    /**
     * 描述：评论数
     */
    private int commentCount;

    /**
     * 类别名称
     */
    private String categoryName;

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    public Date getOperateTime() {
        return super.getOperateTime();
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    public void setOperateTime(Date operateTime) {
        super.setOperateTime(operateTime);
    }

    @Override
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
