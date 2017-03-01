/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.system.controller.client;

import com.icinfo.framework.common.ajax.AjaxResult;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.common.util.EncryptUtils;
import com.icinfo.lpsp.notebook.note.service.IMyFocusService;
import com.icinfo.lpsp.notebook.system.enums.EUser;
import com.icinfo.lpsp.notebook.system.model.User;
import com.icinfo.lpsp.notebook.system.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Properties;


/**
 * 描述: 用户表 NBOOK_USER 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
@Controller
@RequestMapping("/client/")
public class UserController extends BaseController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 注入用户服务
     */
    @Autowired
    private IUserService userService;

    /**
     * 注入我的关注服务
     */
    @Autowired
    private IMyFocusService myFocusService;

    /**
     * 进入注册页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toregister", method = RequestMethod.GET)
    public String toRegister() throws Exception {
        return "client/login/register";
    }

    /**
     * 描述：注册用户
     *
     * @param user 用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<String> register(User user) throws Exception {
        User dbUser = userService.getUserByEmail(user.getEmail());

        //1.判断邮箱是否已注册
        if (dbUser != null) {
            // 是否已激活（已激活）
            if (!dbUser.getStatus().equals(EUser.USER_STATUS_NO_ACTIVATE.getValue())) {
                return new AjaxResponse<>("12", "该邮箱已注册！");
            }
            // 未激活
            userService.removeUserByEmail(dbUser.getEmail());
            //删除初始化我的类别
            myFocusService.deleteMyFocus(dbUser.getId());
        }
        //2.注册用户
        userService.save(user);

        // 3.发送邮件激活,若发送失败则返回错误信息
        if (!userService.sendActivateEmail(user.getId(), user.getEmail())) {
            // 删除用户信息及关注
            userService.removeUserByEmail(user.getEmail());
            myFocusService.deleteMyFocus(user.getId());

            //邮箱发送失败，设置错误信息
            return new AjaxResponse<>("13", "发送失败，请重试！");
        }
        return new AjaxResponse<>(getEmailAddress(user.getEmail()));
    }

    /**
     * 描述：进入激活邮箱结果页面
     *
     * @param id    用户id
     * @param model 模型
     * @return 激活邮箱结果页面
     * @throws Exception
     */
    @RequestMapping(value = "activateemailresult/{id}", method = RequestMethod.GET)
    public String activateEmailResult(@PathVariable("id") String id, Model model) throws Exception {
        User user = userService.getUser(id);
        // 1.用户不存在
        if (user == null) {
            model.addAttribute("resultInfo", "账号不存在！");
        } else {
            // 2.判断激活时间是否超时
            if (!userService.isNotTimeout(user.getActivateTime())) {
                //设置超时状态
                model.addAttribute("overtime", true);
                model.addAttribute("resultInfo", "激活时间超时，");
            } else {
                // 3.激活成功，修改用户状态：正常
                user.setStatus(EUser.USER_STATUS_NORMAL.getValue());
                userService.updateStatus(user);
                model.addAttribute("resultInfo", "激活成功！");
            }
        }
        return "client/login/activateEmailResult";
    }

    /**
     * 描述：进入激活邮箱页面
     *
     * @return 激活邮箱页面
     * @throws Exception
     */
    @RequestMapping(value = "toactivateemail", method = RequestMethod.GET)
    public String toActivateEmail() throws Exception {
        return "client/login/activateEmail";
    }

    /**
     * 发送激活邮箱
     *
     * @param email 邮箱
     * @return 邮件发送结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "sendactivateemail", method = RequestMethod.GET)
    public AjaxResponse<Boolean> sendActivateEmail(String email) throws Exception {
        User user = userService.getUserByEmail(email);
        //1.若邮箱用户不存在，则设置错误信息
        if (user == null) {
            return new AjaxResponse<>("11", "账号不存在");
        } else if (!user.getStatus().equals(EUser.USER_STATUS_NO_ACTIVATE.getValue())) {
            return new AjaxResponse<>("12", "该邮箱已激活");
        }

        if (userService.sendActivateEmail(user.getId(), email)) {
            //邮箱发送成功，设置true
            return new AjaxResponse<>(true);
        } else {
            //邮箱发送失败，设置错误信息
            return new AjaxResponse<>("13", "发送失败，请重试！");
        }
    }

    /**
     * 进入发送邮件成功页（激活邮箱、找回密码）
     *
     * @return 发送邮件成功页
     * @throws Exception
     */
    @RequestMapping(value = "sendemailsuccess/{info}", method = RequestMethod.GET)
    public String sendEmailSuccess(@PathVariable("info") String info, Model model) throws Exception {
        model.addAttribute("emailInfo", info);
        return "client/login/sendEmailSuccess";
    }

    /**
     * 进入找回密码页面
     *
     * @return 找回密码页面
     * @throws Exception
     */
    @RequestMapping("toretrievepass")
    public String toRetrievePass() throws Exception {
        return "client/login/retrievePass";
    }

    /**
     * 描述：跳转至密码修改页
     *
     * @param id 用户id
     * @return 密码修改页
     * @throws Exception
     */
    @RequestMapping(value = "changepassword/{id}", method = RequestMethod.GET)
    public String changePassword(@PathVariable("id") String id,
                                 Model model) throws Exception {
        model.addAttribute("id", id);
        //1.根据id获取用户
        User user = userService.getUser(EncryptUtils.base64Decoder(id,"utf-8"));
        //2.若未获取到则抛异常
        if (user == null) {
            throw new BusinessException("无该用户");
        }
        //3.判断时间是否超时
        if (!userService.isNotTimeout(user.getPasswordModifyTime())) {
            //设置超时状态
            model.addAttribute("overtime", true);
        }
        return "client/login/changePassword";
    }

    /**
     * 描述：密码修改
     *
     * @param password 新密码
     * @param id       id
     * @return 密码修改成功：true；密码修改失败：false
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "modifypass", method = RequestMethod.POST)
    public AjaxResponse<Boolean> modifyPass(String password, String id) throws Exception {
        return new AjaxResponse<>(userService.updatePassword(password, EncryptUtils.base64Decoder(id,"utf-8")));
    }

    /**
     * 描述：发送邮箱
     *
     * @param email 邮箱
     * @return 邮件发送结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/sendemail", method = RequestMethod.GET)
    public AjaxResponse<Boolean> sendEmail(String email) throws Exception {
        User user = userService.getUserByEmail(email);
        //1.若邮箱用户不存在，则设置错误信息
        if (user == null) {
            return new AjaxResponse<>("11", "账号不存在");
        }
        AjaxResponse<Boolean> response = new AjaxResponse<>(false);
        //2.若用户存在，设置密码修改时间


        if (userService.sendPasswordModifyEmail(user.getId(), email)) {
            //邮箱发送成功，设置true
            response.setData(true);
        } else {
            //邮箱发送失败，设置错误信息
            response.setMsg("发送失败，请重试！");
            response.setStatus("12");
        }
        return response;
    }


    /**
     * 描述：根据邮箱获取登录地址
     *
     * @param email 邮箱地址
     * @return 邮箱登录地址
     * @throws Exception
     */
    private String getEmailAddress(String email) throws Exception {
        //1.获取邮箱域名
        String str = email.substring(email.indexOf('@') + 1, email.indexOf('.'));
        //2.获取文件中所有数据
        Properties properties = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("email.properties");
        properties.load(is);
        return properties.getProperty(str);
    }

    /**
     * 描述：我的笔记模块在session中设置是否收缩标识
     *
     * @param flex    收缩标识 true:收缩， false:不收缩
     * @param request 请求
     * @return 返回结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/setflexsession", method = RequestMethod.GET)
    public AjaxResult setFlexSession(@RequestParam(name = "flex", required = true) String flex, HttpServletRequest request) throws Exception{
        request.getSession().setAttribute("flex", flex);
        return AjaxResult.success("设置收缩标识session成功");
    }
}