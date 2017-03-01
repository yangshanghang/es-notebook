/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.lpsp.notebook.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.framework.mybatis.mapper.annotation.Before;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 描述: 用户表 NBOOK_USER 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年12月05日
 */
@Table(name = "NBOOK_USER")
public class User implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String id;

    /**
     * 用户邮箱（账号）
     */
    @Column(name = "NU_EMAIL")
    private String email;

    /**
     * 用户密码
     */
    @Column(name = "NU_PASSWORD")
    private String password;

    /**
     * 真实姓名
     */
    @Column(name = "NU_REAL_NAME")
    private String realName;

    /**
     * QQ号
     */
    @Column(name = "NU_QQ")
    private String qq;

    /**
     * MD5加盐值
     */
    @Column(name = "NU_ENCRYPT_SALT")
    private String encryptSalt;

    /**
     * 用户类型  0：系统用户，  1：普通用户
     */
    @Column(name = "NU_TYPE")
    private String type;

    /**
     * 用户状态  0：锁定， 1：正常， 2：未激活
     */
    @Column(name = "NU_STATUS")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "NU_CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 操作时间
     */
    @Column(name = "NU_OPERATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

    /**
     * 激活时间
     */
    @Column(name = "NU_ACTIVATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date activateTime;

    /**
     * 密码修改时间
     */
    @Column(name = "NU_PASSWORD_MODIFY_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date passwordModifyTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取唯一标识
     *
     * @return ID - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户邮箱（账号）
     *
     * @return NU_EMAIL - 用户邮箱（账号）
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱（账号）
     *
     * @param email 用户邮箱（账号）
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户密码
     *
     * @return NU_PASSWORD - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真实姓名
     *
     * @return NU_REAL_NAME - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取QQ号
     *
     * @return NU_QQ - QQ号
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号
     *
     * @param qq QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取MD5加盐值
     *
     * @return NU_ENCRYPT_SALT - MD5加盐值
     */
    public String getEncryptSalt() {
        return encryptSalt;
    }

    /**
     * 设置MD5加盐值
     *
     * @param encryptSalt MD5加盐值
     */
    public void setEncryptSalt(String encryptSalt) {
        this.encryptSalt = encryptSalt;
    }

    /**
     * 获取用户类型  0：系统用户，  1：普通用户
     *
     * @return NU_TYPE - 用户类型  0：系统用户，  1：普通用户
     */
    public String getType() {
        return type;
    }

    /**
     * 设置用户类型  0：系统用户，  1：普通用户
     *
     * @param type 用户类型  0：系统用户，  1：普通用户
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取用户状态  0：锁定， 1：正常， 2：未激活
     *
     * @return NU_STATUS - 用户状态  0：锁定， 1：正常， 2：未激活
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置用户状态  0：锁定， 1：正常， 2：未激活
     *
     * @param status 用户状态  0：锁定， 1：正常， 2：未激活
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return NU_CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作时间
     *
     * @return NU_OPERATE_TIME - 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取激活时间
     *
     * @return NU_ACTIVATE_TIME - 激活时间
     */
    public Date getActivateTime() {
        return activateTime;
    }

    /**
     * 设置激活时间
     *
     * @param activateTime 激活时间
     */
    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    /**
     * 获取密码修改时间
     *
     * @return NU_PASSWORD_MODIFY_TIME - 密码修改时间
     */
    public Date getPasswordModifyTime() {
        return passwordModifyTime;
    }

    /**
     * 设置密码修改时间
     *
     * @param passwordModifyTime 密码修改时间
     */
    public void setPasswordModifyTime(Date passwordModifyTime) {
        this.passwordModifyTime = passwordModifyTime;
    }
}