package com.icinfo.lpsp.notebook.note.dto;

/**
 * 描述: 类目笔记数结果Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/11/30
 */
public class CategoryResultDto {
    /**
     * 类目名
     */
    private String key;

    /**
     * 笔记数
     */
    private int doc_count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDoc_count() {
        return doc_count;
    }

    public void setDoc_count(int doc_count) {
        this.doc_count = doc_count;
    }
}
