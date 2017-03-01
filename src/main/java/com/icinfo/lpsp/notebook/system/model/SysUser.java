/*
 *  Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.lpsp.notebook.system.model;

import com.icinfo.framework.mybatis.mapper.annotation.Before;
import javax.persistence.*;

/**
 * 描述:  SysUser 实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年04月11日
 */
@Table(name = "FRAMEWORK_SYS_USER")
public class SysUser {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String id;

    /**
     * 登录名
     */
    @Column(name = "SU_LOGIN_NAME")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "SU_LOGIN_PASS")
    private String loginPass;

    /**
     * 获取ID
     *
     * @return ID - ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取登录名
     *
     * @return SU_LOGIN_NAME - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取登录密码
     *
     * @return SU_LOGIN_PASS - 登录密码
     */
    public String getLoginPass() {
        return loginPass;
    }

    /**
     * 设置登录密码
     *
     * @param loginPass 登录密码
     */
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
}