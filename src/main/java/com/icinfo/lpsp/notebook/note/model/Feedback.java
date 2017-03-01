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
 * 描述: 意见反馈表 NBOOK_FEEDBACK 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年11月30日
 */
@Table(name = "NBOOK_FEEDBACK")
public class Feedback implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String id;

    /**
     * 用户id
     */
    @Column(name = "NF_USER_ID")
    private String userId;

    /**
     * 反馈内容
     */
    @Column(name = "NF_CONTENT")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "NF_CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

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
     * 获取用户id
     *
     * @return NF_USER_ID - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取反馈内容
     *
     * @return NF_CONTENT - 反馈内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置反馈内容
     *
     * @param content 反馈内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建时间
     *
     * @return NF_CREATE_TIME - 创建时间
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
}