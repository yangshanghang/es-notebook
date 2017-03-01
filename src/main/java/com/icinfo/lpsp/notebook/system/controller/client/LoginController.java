/*
 *  Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.lpsp.notebook.system.controller.client;

import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.common.util.EncryptUtils;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import com.icinfo.lpsp.notebook.system.enums.EUser;
import com.icinfo.lpsp.notebook.system.model.User;
import com.icinfo.lpsp.notebook.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 */
@Controller("clientLoginController")
@RequestMapping("/client/")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class LoginController extends BaseController {
    /**
     * 注入笔记服务
     */
    @Autowired
    private INoteService noteService;

    /**
     * 注入用户服务
     */
    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     *
     * @param user 用户
     * @return 首页
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> login(User user, Model model) throws Exception {
        // 1.根据邮箱获取用户
        User dbUser = userService.getUserByEmail(user.getEmail());
        // 2.用户不存在
        if (dbUser == null) {
            return new AjaxResponse<>(false);
        }
        // 3.输入的密码 + 数据库获取的盐值 进行MD5密码加密
        String passwordMD5 = EncryptUtils.encryptPassword(user.getPassword(), dbUser.getEncryptSalt());
        // 4.将获得的加密密码, 与数据库存储的密码比较
        if (passwordMD5.equals(dbUser.getPassword())) {
            //5.若用户被锁定
            if (dbUser.getStatus().equals(EUser.USER_STATUS_LOCK.getValue())) {
                return new AjaxResponse<>("14", "用户被锁定");
            }
            //6.若用户邮箱未激活
            if (dbUser.getStatus().equals(EUser.USER_STATUS_NO_ACTIVATE.getValue())) {
                return new AjaxResponse<>("15", "用户未激活");
            }
            // 7.设置用户信息到session
            model.addAttribute(Constant.SESSION_USER_INFO, dbUser);
            return new AjaxResponse<>(true);
        }
        return new AjaxResponse<>(false);
    }

    /**
     * 进入首页
     *
     * @return 首页
     */
    @RequestMapping("index")
    public String index(Model model) throws Exception {
        model.addAttribute("map", noteService.getShowColumnNote());
        return "client/note/index";
    }

    /**
     * 进入登录页
     *
     * @return 登录页
     */
    @RequestMapping("tologin")
    public String toLogin(@RequestParam(value = "ref", required = false) String ref, Model model, HttpServletRequest request) throws Exception {

        //1.获取用户session对象
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_INFO);
        //2.若不为空，则灵活跳转登录后页面
        if (user != null) {
            String url = request.getHeader("referer");
            if (StringUtils.isBlank(url)) {
                return "redirect:index";
            } else if ("index".equals(ref)) {
                return "redirect:/";
            } else {
                return "redirect:" + url;
            }
        }
        model.addAttribute("ref", ref);
        return "client/login/login";
    }

    /**
     * 用户退出
     *
     * @param sessionStatus session
     * @return 登录页面
     */
    @RequestMapping("logout")
    public String logout(SessionStatus sessionStatus) {
        // 失效用户session
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
