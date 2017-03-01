/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.lpsp.notebook.note.mapper.FeedbackMapper;
import com.icinfo.lpsp.notebook.note.model.Feedback;
import com.icinfo.lpsp.notebook.note.service.IFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述: 意见反馈表 NBOOK_FEEDBACK 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月30日
 */
@Service
public class FeedbackServiceImpl extends MyBatisServiceSupport implements IFeedbackService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    /**
     * 注入意见反馈mapper
     */
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 保存反馈意见
     *
     * @param feedback 反馈意见
     * @throws Exception
     */
    @Override
    public void save(Feedback feedback) throws Exception {
        feedback.setCreateTime(new Date());
        feedbackMapper.insert(feedback);
    }
}