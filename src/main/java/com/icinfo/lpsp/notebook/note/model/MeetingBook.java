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
 * 描述:  NBOOK_MEETING_BOOK 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2017年02月09日
 */
@Table(name = "NBOOK_MEETING_BOOK")
public class MeetingBook implements Serializable {
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
    @Column(name = "NMB_USER_ID")
    private String userId;

    /**
     * 会议室类别 （1：会议室1； 2：会议室2）
     */
    @Column(name = "NMB_MEETING_TYPE")
    private String meetingType;

    /**
     * 主题
     */
    @Column(name = "NMB_THEME")
    private String theme;

    /**
     * 预定者
     */
    @Column(name = "NMB_BOOKER")
    private String booker;

    /**
     * 所属部门
     */
    @Column(name = "NMB_DEPARTMENT")
    private String department;

    /**
     * 开始时间
     */
    @Column(name = "NMB_START_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "NMB_END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

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
     * @return NMB_USER_ID - 用户id
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
     * 获取会议室类别 （1：会议室1； 2：会议室2）
     *
     * @return NMB_MEETING_TYPE - 会议室类别 （1：会议室1； 2：会议室2）
     */
    public String getMeetingType() {
        return meetingType;
    }

    /**
     * 设置会议室类别 （1：会议室1； 2：会议室2）
     *
     * @param meetingType 会议室类别 （1：会议室1； 2：会议室2）
     */
    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    /**
     * 获取主题
     *
     * @return NMB_THEME - 主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置主题
     *
     * @param theme 主题
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * 获取预定者
     *
     * @return NMB_BOOKER - 预定者
     */
    public String getBooker() {
        return booker;
    }

    /**
     * 设置预定者
     *
     * @param booker 预定者
     */
    public void setBooker(String booker) {
        this.booker = booker;
    }

    /**
     * 获取所属部门
     *
     * @return NMB_DEPARTMENT - 所属部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置所属部门
     *
     * @param department 所属部门
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 获取开始时间
     *
     * @return NMB_START_TIME - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return NMB_END_TIME - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}