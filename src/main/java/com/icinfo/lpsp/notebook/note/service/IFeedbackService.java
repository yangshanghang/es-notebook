/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.note.model.Feedback;

/**
 * 描述: 意见反馈表 NBOOK_FEEDBACK 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月30日
 */
public interface IFeedbackService extends BaseService {
    /**
     * 保存反馈意见
     *
     * @param feedback 反馈意见
     * @throws Exception
     */
    void save(Feedback feedback) throws Exception;
}