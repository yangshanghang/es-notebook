/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.note.model.MyFocus;

import java.util.List;

/**
 * 描述: 我的关注表 NBOOK_MY_ATTENTION 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public interface IMyFocusService extends BaseService {

    /**
     * 描述：保存我的关注
     *
     * @param id          用户id
     * @param myFocusList 我的关注列表
     * @return 保存我的关注结果
     * @throws Exception
     */
    boolean saveList(String id, List<MyFocus> myFocusList) throws Exception;


    /**
     * 描述：初始化我的关注
     *
     * @param userId 用户id
     * @throws Exception
     */
    void initMyFocus(String userId) throws Exception;

    /**
     * 描述：删除我的关注
     *
     * @param userId 用户id
     * @throws Exception
     */
    void deleteMyFocus(String userId) throws Exception;
}