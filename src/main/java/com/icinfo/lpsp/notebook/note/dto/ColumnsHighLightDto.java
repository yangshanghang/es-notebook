package com.icinfo.lpsp.notebook.note.dto;

/**
 * 描述: 专栏高亮Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/12/29
 */
public class ColumnsHighLightDto {
    /**
     * 专栏名称
     */
    private String[] name;

    /**
     * 专栏介绍
     */
    private String[] introduce;

    /**
     * 类目名称
     */
    private String[] categoryName;

    /**
     * 创建者
     */
    private String[] author;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String[] introduce) {
        this.introduce = introduce;
    }

    public String[] getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String[] categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }
}
