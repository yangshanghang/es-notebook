package com.icinfo.lpsp.notebook.note.dto;

import java.util.List;

/**
 * 描述: ES专栏数据Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/12/29
 */
public class ESColumnsDataDto {
    /**
     * 用时(毫秒)
     */
    private long took;

    /**
     * 笔记总量
     */
    private int total;

    /**
     * 最大分数
     */
    private String max_score;

    /**
     * 数据列表
     */
    private List<ColumnsHitsDto> hits;

    public List<ColumnsHitsDto> getHits() {
        return hits;
    }

    public void setHits(List<ColumnsHitsDto> hits) {
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
