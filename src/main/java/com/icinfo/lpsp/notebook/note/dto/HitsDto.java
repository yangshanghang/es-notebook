package com.icinfo.lpsp.notebook.note.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Administrator on 2016/11/10.
 */
public class HitsDto {

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
     * 笔记数据源
     */
    private NoteDto _source;

    /**
     * 高亮
     */
    private HighLightDto highlight;

    /**
     * 排序
     */
    @JsonIgnore
    private String sort;

    /**
     * score
     */
    private String _score;

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

    public NoteDto get_source() {
        return _source;
    }

    public void set_source(NoteDto _source) {
        this._source = _source;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public HighLightDto getHighlight() {
        return highlight;
    }

    public void setHighlight(HighLightDto highlight) {
        this.highlight = highlight;
    }

    @Override
    public String toString() {
        return "HitsDto{" +
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
