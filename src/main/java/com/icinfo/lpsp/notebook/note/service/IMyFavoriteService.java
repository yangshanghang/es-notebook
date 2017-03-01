/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;

/**
 * 描述: 我的收藏表 NBOOK_MY_COLLECT 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月22日
 */
public interface IMyFavoriteService extends BaseService {

    /**
     * 描述：保存 我的收藏
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @throws Exception
     */
    boolean save(String userId, String noteId) throws Exception;

    /**
     * 描述：删除 我的收藏
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @throws Exception
     */
    boolean remove(String userId, String noteId) throws Exception;

    /**
     * 描述：获取我的收藏列表
     *
     * @param userId         用户id
     * @param categoryId     类别id
     * @param queryParamLike 查询参数
     * @param pageNum        页码
     * @param pageSize       页数
     * @return 我的收藏列表
     * @throws Exception
     */
    PageInfo<NoteDto> getMyFavoriteList(String userId, String categoryId, String queryParamLike, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：判断用户是否已收藏此笔记
     *
     * @param userId 用户id
     * @param noteId 笔记id
     * @return true： 笔记已收藏 false：笔记未收藏
     * @throws Exception
     */
    boolean isFavorite(String userId, String noteId) throws Exception;
}