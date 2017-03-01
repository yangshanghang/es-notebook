package com.icinfo.lpsp.notebook.note.dto;

/**
 * 描述: ES聚合数据Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/11/30
 */
public class ESAggDataDto {
    /**
     * 类目聚合Dto
     */
    private CategoryAggDto categoryAgg;

    public CategoryAggDto getCategoryAgg() {
        return categoryAgg;
    }

    public void setCategoryAgg(CategoryAggDto categoryAgg) {
        this.categoryAgg = categoryAgg;
    }
}
