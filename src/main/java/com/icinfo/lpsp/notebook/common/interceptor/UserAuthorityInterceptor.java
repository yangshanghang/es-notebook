package com.icinfo.lpsp.notebook.common.interceptor;

import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.HttpUtils;
import com.icinfo.lpsp.notebook.system.model.SysUser;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器
 */
public class UserAuthorityInterceptor extends HandlerInterceptorAdapter {
    /**
     * 后台用户权限拦截.
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return true 继续 false终止
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_INFO);
        // 用户为空
        if (user == null) {
            if (HttpUtils.isAjax(request)) {
                HttpUtils.writeError(response, Constant.LOGIN_ERROR_INFO, HttpStatus.UNAUTHORIZED.value());
            } else {
                // 未登录
                response.sendRedirect("/");
            }
            return false;
        }
        return true;
    }
}
