/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.note.model.Feedback;
import com.icinfo.lpsp.notebook.note.service.IFeedbackService;
import com.icinfo.lpsp.notebook.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 意见反馈控制器.<br>
 *
 * @author framework generator
 * @date 2016年11月30日
 */
@Controller("personFeedbackController")
@RequestMapping("/person/feedback")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class FeedbackController extends BaseController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    /**
     * 注入意见反馈服务
     */
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 保存反馈意见
     *
     * @param feedback 反馈意见
     * @return 保存结果
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> add(Feedback feedback, @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
        feedback.setUserId(user.getId());
        feedbackService.save(feedback);
        return new AjaxResponse<>(true);
    }
}