/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.model.Note;

import java.util.Date;
import java.util.List;

/**
 * 描述: 专栏表 NBOOK_COLUMNS 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2016年12月28日
 */
public class ColumnsDto extends Columns {

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 作者
     */
    private String author;

    /**
     * 笔记数量
     */
    private int noteCount;

    /**
     * 笔记列表
     */
    private List<Note> noteList;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }
}