package com.icinfo.lpsp.notebook.system.enums;

/**
 * 描述: 用户枚举 .<br>
 *
 * @author YangShangHang
 * @date 2016/11/24
 */
public enum EUser {
    /**
     * 用户状态：锁定
     */
    USER_STATUS_LOCK("0", "锁定"),

    /**
     * 用户状态：正常
     */
    USER_STATUS_NORMAL("1", "正常"),

    /**
     * 用户状态：未激活
     */
    USER_STATUS_NO_ACTIVATE("2", "未激活"),

    /**
     * 用户类型：系统用户
     */
    USER_TYPE_SYSTEM("0", "系统用户"),

    /**
     * 用户类型：普通用户
     */
    USER_TYPE_COMMON("1", "普通用户");

    private String value;
    private String desc;

    EUser(String value, String desc) {
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
