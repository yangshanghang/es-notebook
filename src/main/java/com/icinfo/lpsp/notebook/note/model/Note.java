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
 * 描述: 笔记表 NBOOK_NOTE 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年12月28日
 */
@Table(name = "NBOOK_NOTE")
public class Note implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String id;

    /**
     * 标题
     */
    @Column(name = "NN_TITLE")
    private String title;

    /**
     * 摘要
     */
    @Column(name = "NN_SUMMARY")
    private String summary;

    /**
     * 缩略图url
     */
    @Column(name = "NN_SCALE_IMG_URL")
    private String scaleImgUrl;

    /**
     * 笔记类型 唯一标识
     */
    @Column(name = "NN_TYPE_ID")
    private String typeId;

    /**
     * 附件标识 0:没有附件 1:有附件
     */
    @Column(name = "NN_ATTACHMENT_FLAG")
    private String attachmentFlag;

    /**
     * 作者
     */
    @Column(name = "NN_AUTHOR")
    private String author;

    /**
     * 用户id（作者id）
     */
    @Column(name = "NN_USER_ID")
    private String userId;

    /**
     * 所属专栏id
     */
    @Column(name = "NN_COLUMN_ID")
    private String columnId;

    /**
     * 专栏笔记序号
     */
    @Column(name = "NN_COLUMN_ORDER_NUMBER")
    private Integer columnOrderNumber;

    /**
     * 操作者
     */
    @Column(name = "NN_OPERATOR")
    private String operator;

    /**
     * 点赞数
     */
    @Column(name = "NN_PRAISE_COUNT")
    private Integer praiseCount;

    /**
     * 可见状态  0：公开  1：仅自己可见
     */
    @Column(name = "NN_VISIBLE_STATUS")
    private String visibleStatus;

    /**
     * 创建时间
     */
    @Column(name = "NN_CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 操作时间
     */
    @Column(name = "NN_OPERATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operateTime;

    /**
     * 内容
     */
    @Column(name = "NN_CONTENT")
    private String content;

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
     * 获取标题
     *
     * @return NN_TITLE - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取摘要
     *
     * @return NN_SUMMARY - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取缩略图url
     *
     * @return NN_SCALE_IMG_URL - 缩略图url
     */
    public String getScaleImgUrl() {
        return scaleImgUrl;
    }

    /**
     * 设置缩略图url
     *
     * @param scaleImgUrl 缩略图url
     */
    public void setScaleImgUrl(String scaleImgUrl) {
        this.scaleImgUrl = scaleImgUrl;
    }

    /**
     * 获取笔记类型 唯一标识
     *
     * @return NN_TYPE_ID - 笔记类型 唯一标识
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置笔记类型 唯一标识
     *
     * @param typeId 笔记类型 唯一标识
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取附件标识 0:没有附件 1:有附件
     *
     * @return NN_ATTACHMENT_FLAG - 附件标识 0:没有附件 1:有附件
     */
    public String getAttachmentFlag() {
        return attachmentFlag;
    }

    /**
     * 设置附件标识 0:没有附件 1:有附件
     *
     * @param attachmentFlag 附件标识 0:没有附件 1:有附件
     */
    public void setAttachmentFlag(String attachmentFlag) {
        this.attachmentFlag = attachmentFlag;
    }

    /**
     * 获取作者
     *
     * @return NN_AUTHOR - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取用户id（作者id）
     *
     * @return NN_USER_ID - 用户id（作者id）
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id（作者id）
     *
     * @param userId 用户id（作者id）
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取所属专栏id
     *
     * @return NN_COLUMN_ID - 所属专栏id
     */
    public String getColumnId() {
        return columnId;
    }

    /**
     * 设置所属专栏id
     *
     * @param columnId 所属专栏id
     */
    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    /**
     * 获取专栏笔记序号
     *
     * @return NN_COLUMN_ORDER_NUMBER - 专栏笔记序号
     */
    public Integer getColumnOrderNumber() {
        return columnOrderNumber;
    }

    /**
     * 设置专栏笔记序号
     *
     * @param columnOrderNumber 专栏笔记序号
     */
    public void setColumnOrderNumber(Integer columnOrderNumber) {
        this.columnOrderNumber = columnOrderNumber;
    }

    /**
     * 获取操作者
     *
     * @return NN_OPERATOR - 操作者
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取点赞数
     *
     * @return NN_PRAISE_COUNT - 点赞数
     */
    public Integer getPraiseCount() {
        return praiseCount;
    }

    /**
     * 设置点赞数
     *
     * @param praiseCount 点赞数
     */
    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * 获取可见状态  0：公开  1：仅自己可见
     *
     * @return NN_VISIBLE_STATUS - 可见状态  0：公开  1：仅自己可见
     */
    public String getVisibleStatus() {
        return visibleStatus;
    }

    /**
     * 设置可见状态  0：公开  1：仅自己可见
     *
     * @param visibleStatus 可见状态  0：公开  1：仅自己可见
     */
    public void setVisibleStatus(String visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    /**
     * 获取创建时间
     *
     * @return NN_CREATE_TIME - 创建时间
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
     * @return NN_OPERATE_TIME - 操作时间
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
     * 获取内容
     *
     * @return NN_CONTENT - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}