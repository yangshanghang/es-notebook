package com.icinfo.lpsp.notebook.note.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 描述: 专栏hits .<br>
 *
 * @author YangShangHang
 * @date 2016/12/29
 */
public class ColumnsHitsDto {
    /**
     * 索引名称
     */
    private String _index;

    /**
     * 索引类型
     */
    private String _type;

    /**
     * 索引id
     */
    private String _id;

    /**
     * 专栏数据源
     */
    private ColumnsDto _source;

    /**
     * 高亮
     */
    private ColumnsHighLightDto highlight;

    /**
     * 排序
     */
    @JsonIgnore
    private String sort;

    /**
     * 分数
     */
    private String _score;

    /**
     * 数量
     */
    private int count = 0;

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ColumnsDto get_source() {
        return _source;
    }

    public void set_source(ColumnsDto _source) {
        this._source = _source;
    }

    public ColumnsHighLightDto getHighlight() {
        return highlight;
    }

    public void setHighlight(ColumnsHighLightDto highlight) {
        this.highlight = highlight;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ColumnsHitsDto{" +
                "_index='" + _index + '\'' +
                ", _type='" + _type + '\'' +
                ", _id='" + _id + '\'' +
                ", _source=" + _source +
                ", highlight=" + highlight +
                ", sort='" + sort + '\'' +
                ", _score='" + _score + '\'' +
                '}';
    }
}
