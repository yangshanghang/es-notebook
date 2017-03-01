/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.system.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.system.model.User;

import java.util.Date;

/**
 * 描述: 用户表 NBOOK_USER 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public interface IUserService extends BaseService {

    /**
     * 描述：保存用户
     *
     * @param user 用户
     * @throws Exception
     */
    void save(User user) throws Exception;

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱地址
     * @return 用户
     * @throws Exception
     */
    User getUserByEmail(String email) throws Exception;

    /**
     * 根据邮箱删除用户
     *
     * @param email 邮箱地址
     * @throws Exception
     */
    void removeUserByEmail(String email) throws Exception;

    /**
     * 描述：根据id获取用户
     *
     * @param id 用户id
     * @return 用户
     * @throws Exception
     */
    User getUser(String id) throws Exception;

    /**
     * 描述：修改用户密码
     *
     * @param password 新密码
     * @param id       用户id
     * @throws Exception
     */
    boolean updatePassword(String password, String id) throws Exception;

    /**
     * 描述：修改用户状态
     *
     * @param user 用户对象
     * @throws Exception
     */
    void updateStatus(User user) throws Exception;

    /**
     * 描述：发送激活邮箱
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @throws Exception
     */
    boolean sendActivateEmail(String userId, String email) throws Exception;

    /**
     * 描述：发送密码修改邮件
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @return 发送成功：true；发送失败：false
     * @throws Exception
     */
    boolean sendPasswordModifyEmail(String userId, String email) throws Exception;

    /**
     * 描述：时间是否超时
     *
     * @param time 时间
     * @return true:未超时， false:超时
     * @throws Exception
     */
    boolean isNotTimeout(Date time) throws Exception;
}