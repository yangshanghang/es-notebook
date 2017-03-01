package com.icinfo.lpsp.notebook.note.dto;

import java.util.List;

/**
 * ES数据Dto
 */
public class ESDataDto {
    /**
     * 用时(毫秒)
     */
    private long took;

    /**
     * 笔记总量
     */
    private int total;

    /**
     * max_score
     */
    private String max_score;

    /**
     * 数据列表
     */
    private List<HitsDto> hits;

    public List<HitsDto> getHits() {
        return hits;
    }

    public void setHits(List<HitsDto> hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMax_score() {
        return max_score;
    }

    public long getTook() {
        return took;
    }

    public void setTook(long took) {
        this.took = took;
    }
}
