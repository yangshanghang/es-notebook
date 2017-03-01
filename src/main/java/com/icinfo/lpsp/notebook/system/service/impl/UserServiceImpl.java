/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.system.service.impl;

import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.lpsp.notebook.common.bean.Mail;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.EncryptUtils;
import com.icinfo.lpsp.notebook.common.util.MailUtil;
import com.icinfo.lpsp.notebook.note.service.IMyFocusService;
import com.icinfo.lpsp.notebook.system.enums.EUser;
import com.icinfo.lpsp.notebook.system.mapper.UserMapper;
import com.icinfo.lpsp.notebook.system.model.User;
import com.icinfo.lpsp.notebook.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述: 用户表 NBOOK_USER 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
@Service
public class UserServiceImpl extends MyBatisServiceSupport implements IUserService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 应用地址
     */
    @Value("${application.url}")
    private String applicationUrl;

    /**
     * 邮件服务器地址
     */
    @Value("${email.host}")
    private String emailHost;

    /**
     * 发件人
     */
    @Value("${email.sender}")
    private String emailSender;

    /**
     * 发件人昵称
     */
    @Value("${email.sender.name}")
    private String emailSenderName;

    /**
     * 发件人登录邮箱服务器账号
     */
    @Value("${email.sender.username}")
    private String emailSenderUsername;

    /**
     * 发件人登录邮箱服务器密码
     */
    @Value("${email.sender.password}")
    private String emailSenderPassword;

    @Autowired
    private IMyFocusService myFocusService;

    /**
     * 描述：保存用户
     *
     * @param user 用户
     * @throws Exception
     */
    @Override
    public void save(User user) throws Exception {
        user.setStatus(EUser.USER_STATUS_NO_ACTIVATE.getValue());
        user.setType(EUser.USER_TYPE_COMMON.getValue());
        EncryptUtils.encryptPassword(user);
        user.setCreateTime(new Date());
        user.setOperateTime(user.getCreateTime());
        userMapper.insert(user);
        //初始化用户关注类别
        myFocusService.initMyFocus(user.getId());
    }

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱地址
     * @return 用户
     * @throws Exception
     */
    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        return userMapper.selectOne(user);
    }

    /**
     * 根据邮箱删除用户
     *
     * @param email 邮箱地址
     * @throws Exception
     */
    @Override
    public void removeUserByEmail(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        userMapper.delete(user);
    }

    /**
     * 描述：根据id获取用户
     *
     * @param id 用户id
     * @return 用户
     * @throws Exception
     */
    @Override
    public User getUser(String id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 描述：修改用户密码
     *
     * @param password 新密码
     * @param id       用户id
     * @throws Exception
     */
    @Override
    public boolean updatePassword(String password, String id) throws Exception {
        //1.根据id获取用户
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return false;
        }
        //2.设置新密码
        user.setPassword(password);
        //3.设置加盐值
        EncryptUtils.encryptPassword(user);
        //修改用户密码
        userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    /**
     * 描述：修改用户状态
     *
     * @param user 用户对象
     * @throws Exception
     */
    @Override
    public void updateStatus(User user) throws Exception {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 描述：发送激活邮箱
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @throws Exception
     */
    @Override
    public boolean sendActivateEmail(String userId, String email) throws Exception {
        //1.从发送激活邮件
        Mail mail = new Mail(emailHost, emailSender, emailSenderName, emailSenderUsername, emailSenderPassword);
        //设置收件人邮箱
        mail.setReceiver(email);
        //设置收件人名称
        mail.setReceiverName("我");
        //设置主体
        mail.setSubject("激活你的筑巢笔记账号");
        //设置内容 其包可以包括html
        mail.setMessage("<span>你好！</span><br><br>" +
                "<span>感谢你注册筑巢笔记。</span><br>" +
                "<span>你的登录邮箱为：</span>" + email + "。<span>请点击以下链接激活账号：</span><br>" +
                applicationUrl + "client/activateemailresult/" + userId + "<br>" +
                "<span>如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入筑巢笔记。（该链接在15分钟内有效，15分钟后需要重新激活）</span>");
        //发送邮件
        Boolean flag = new MailUtil().send(mail);

        //2.发送成功设置激活时间
        if (flag) {
            User user = new User();
            user.setId(userId);
            user.setActivateTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        }
        return flag;
    }

    /**
     * 描述：发送密码修改邮件
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @return 发送成功：true；发送失败：false
     * @throws Exception
     */
    @Override
    public boolean sendPasswordModifyEmail(String userId, String email) throws Exception {
        String base64Id = EncryptUtils.base64Encoder(userId, "utf-8");
        // 1.设置邮箱
        Mail mail = new Mail(emailHost, emailSender, emailSenderName, emailSenderUsername, emailSenderPassword);
        //设置收件人邮箱
        mail.setReceiver(email);
        //设置收件人名称
        mail.setReceiverName("我");
        //设置主体
        mail.setSubject("找回筑巢笔记密码");
        //设置内容 其包可以包括html
        mail.setMessage("<span>忘记筑巢笔记密码了吗？别着急，请点击以下链接，我们协助您找回密码：</span><br>" +
                applicationUrl + "client/changepassword/" + base64Id + "<br><br>" +
                "<span>如果这不是您的邮件请忽略，很抱歉打扰您，请原谅。</span><br>");
        // 2.发送邮件
        Boolean flag = new MailUtil().send(mail);

        // 3.更新密码修改时间
        if (flag) {
            User user = new User();
            user.setId(userId);
            user.setPasswordModifyTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        }
        return flag;
    }

    /**
     * 描述：时间是否超时
     *
     * @param time 时间
     * @return true:未超时， false:超时
     * @throws Exception
     */
    @Override
    public boolean isNotTimeout(Date time) throws Exception {
        return (time.getTime() + Constant.TIME_OUT) > new Date().getTime() ? true : false;
    }
}