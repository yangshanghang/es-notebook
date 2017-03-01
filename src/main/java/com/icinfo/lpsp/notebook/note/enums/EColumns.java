package com.icinfo.lpsp.notebook.note.enums;

/**
 * 描述: 专栏枚举 .<br>
 *
 * @author YangShangHang
 * @date 2016/12/30
 */
public enum EColumns {
    /**
     * 可见状态：公开
     */
    VISIBLE_STATUS_PUBLIC("0", "公开"),

    /**
     * 可见状态：仅自己可见
     */
    VISIBLE_STATUS_PERSON("1", "仅自己可见");

    private String value;
    private String desc;

    EColumns(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
