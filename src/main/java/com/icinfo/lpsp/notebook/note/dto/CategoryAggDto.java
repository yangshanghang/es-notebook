package com.icinfo.lpsp.notebook.note.dto;

import java.util.List;

/**
 * 描述: 类目聚合Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/11/30
 */
public class CategoryAggDto {

    private int doc_count_error_upper_bound;

    private int sum_other_doc_count;

    /**
     * 类目笔记数结果Dto
     */
    private List<CategoryResultDto> buckets;

    public int getDoc_count_error_upper_bound() {
        return doc_count_error_upper_bound;
    }

    public void setDoc_count_error_upper_bound(int doc_count_error_upper_bound) {
        this.doc_count_error_upper_bound = doc_count_error_upper_bound;
    }

    public int getSum_other_doc_count() {
        return sum_other_doc_count;
    }

    public void setSum_other_doc_count(int sum_other_doc_count) {
        this.sum_other_doc_count = sum_other_doc_count;
    }

    public List<CategoryResultDto> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<CategoryResultDto> buckets) {
        this.buckets = buckets;
    }
}
