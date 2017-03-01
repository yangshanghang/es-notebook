package com.icinfo.lpsp.notebook.note.enums;

/**
 * 描述：笔记枚举
 */
public enum ENote {
    /**
     * 附件标识：无
     */
    ATTACHMENT_FLAG_NO("0", "无"),

    /**
     * 附件标识：有
     */
    ATTACHMENT_FLAG_IS("1", "有"),

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

    ENote(String value, String desc) {
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
