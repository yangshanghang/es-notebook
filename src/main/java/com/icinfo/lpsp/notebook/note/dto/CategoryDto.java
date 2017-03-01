package com.icinfo.lpsp.notebook.note.dto;

import com.icinfo.lpsp.notebook.note.model.Category;

/**
 * 描述: 笔记类目Dto .<br>
 *
 * @author YangShangHang
 * @date 2016/11/25
 */
public class CategoryDto extends Category {
    /**
     * 笔记数量
     */
    private int noteCount = 0;

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }
}
