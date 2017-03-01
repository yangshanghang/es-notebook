package com.icinfo.lpsp.notebook.note.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述：高亮model
 */
public class HighLightDto {
    /**
     * 标题
     */
    private String[] title;

    /**
     * 摘要
     */
    private String[] summary;

    /**
     * 类别名称
     */
    @JsonProperty("category.name")
    private String[] name;

    /**
     * 创建者
     */
    private String[] author;
    /**
     * 操作者
     */
    private String[] operator;

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getSummary() {
        return summary;
    }

    public void setSummary(String[] summary) {
        this.summary = summary;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String[] getOperator() {
        return operator;
    }

    public void setOperator(String[] operator) {
        this.operator = operator;
    }
}
