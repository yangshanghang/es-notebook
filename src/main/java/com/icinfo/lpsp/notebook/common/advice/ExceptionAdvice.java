/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author YuShunWei
 * @date 2016年3月7日
 * @version 1.0
 */
package com.icinfo.lpsp.notebook.common.advice;

import com.icinfo.lpsp.notebook.common.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后端异常处理
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 服务器运行异常处理
     *
     * @param ex 异常
     * @return 返回错误信息
     */
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Exception ex) throws Exception {
        // 记录异常日志
        logger.error("服务器运行异常:", ex);

        // 判定是否为异步请求，非异步请求直接进入error页面
        if(!HttpUtils.isAjax(request)){
            return "error";
        }

        // 异步请求错误处理
        HttpUtils.writeError(response,"系统繁忙", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return null;
    }
}
