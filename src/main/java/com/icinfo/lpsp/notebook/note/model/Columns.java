/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.lpsp.notebook.note.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.framework.mybatis.mapper.annotation.Before;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 描述: 专栏表 NBOOK_COLUMNS 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年12月29日
 */
@Table(name = "NBOOK_COLUMNS")
public class Columns implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "NC_USER_ID")
    private String userId;

    /**
     * 类目id
     */
    @Column(name = "NC_CATEGORY_ID")
    private String categoryId;

    /**
     * 专栏名称
     */
    @Column(name = "NC_NAME")
    private String name;

    /**
     * 专栏图标
     */
    @Column(name = "NC_ICON_URL")
    private String iconUrl;

    /**
     * 专栏介绍
     */
    @Column(name = "NC_INTRODUCE")
    private String introduce;

    /**
     * 是否可见（0：可见， 1：不可见）
     */
    @Column(name = "NC_VISIBLE_STATUS")
    private String visibleStatus;

    /**
     * 创建时间
     */
    @Column(name = "NC_CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 操作时间
     */
    @Column(name = "NC_OPERATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

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
     * 获取用户ID
     *
     * @return NC_USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取类目id
     *
     * @return NC_CATEGORY_ID - 类目id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类目id
     *
     * @param categoryId 类目id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取专栏名称
     *
     * @return NC_NAME - 专栏名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置专栏名称
     *
     * @param name 专栏名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取专栏图标
     *
     * @return NC_ICON_URL - 专栏图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置专栏图标
     *
     * @param iconUrl 专栏图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取专栏介绍
     *
     * @return NC_INTRODUCE - 专栏介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置专栏介绍
     *
     * @param introduce 专栏介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取是否可见（0：可见， 1：不可见）
     *
     * @return NC_VISIBLE_STATUS - 是否可见（0：可见， 1：不可见）
     */
    public String getVisibleStatus() {
        return visibleStatus;
    }

    /**
     * 设置是否可见（0：可见， 1：不可见）
     *
     * @param visibleStatus 是否可见（0：可见， 1：不可见）
     */
    public void setVisibleStatus(String visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    /**
     * 获取创建时间
     *
     * @return NC_CREATE_TIME - 创建时间
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
     * @return NC_OPERATE_TIME - 操作时间
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

    @Override
    public String toString() {
        return "Columns{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", introduce='" + introduce + '\'' +
                ", visibleStatus='" + visibleStatus + '\'' +
                ", createTime=" + createTime +
                ", operateTime=" + operateTime +
                '}';
    }
}