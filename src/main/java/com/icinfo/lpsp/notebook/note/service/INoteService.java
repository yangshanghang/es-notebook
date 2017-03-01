/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.model.Note;

import java.util.List;
import java.util.Map;

/**
 * 描述:  NBOOK_NOTE 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年11月15日
 */
public interface INoteService extends BaseService {

    /**
     * 描述：ES分页查询笔记
     *
     * @param userId     用户Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 笔记列表
     * @throws Exception
     */
    ESDataDto getList(String userId, String queryParam, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：ES根据类型查询
     *
     * @param userId     用户Id
     * @param categoryId 类型Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 笔记列表
     * @throws Exception
     */
    ESDataDto getTypeQueryList(String userId, String categoryId, String queryParam, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：ES根据索引id 查找笔记
     *
     * @param indexId 索引ID
     * @return 笔记详情
     * @throws Exception
     */
    NoteDto get(String indexId) throws Exception;

    /**
     * 描述：根据笔记id获取 笔记
     *
     * @param noteId 笔记id
     * @return 笔记
     * @throws Exception
     */
    Note getNote(String noteId) throws Exception;

    /**
     * 描述：插入或更新笔记
     *
     * @param note 笔记
     * @throws Exception
     */
    String insertOrUpdate(NoteDto note) throws Exception;

    /**
     * 删除笔记
     *
     * @param indexId 笔记id
     * @return 删除结果  成功：true， 失败：false
     * @throws Exception
     */
    void remove(String indexId) throws Exception;

    /**
     * 描述：获取展示栏笔记集合
     *
     * @return 展示栏笔记集合
     * @throws Exception
     */
    Map<String, List<Note>> getShowColumnNote() throws Exception;

    /**
     * 描述：点赞
     *
     * @param indexId 笔记id
     * @throws Exception
     */
    void pushPraise(String indexId) throws Exception;


    /**
     * 描述：根据专栏状态 修改笔记状态
     *
     * @param note 笔记对象
     * @throws Exception
     */
    void modifyStatus(Note note) throws Exception;

    /**
     * 描述：清空专栏Id
     *
     * @param columnId 专栏ID
     * @throws Exception
     */
    void emptyColumnId(String columnId) throws Exception;

    /**
     * 获取专栏中的笔记列表
     *
     * @param columnId 专栏id
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 专栏笔记列表
     * @throws Exception
     */
    ESDataDto getColumnsNoteList(String columnId, int pageNum, int pageSize) throws Exception;

    /**
     * 获取上一篇下一篇笔记
     *
     * @param noteId 笔记id
     * @return 上一篇下一篇笔记
     * @throws Exception
     */
    Map<String, Note> getPrevAndNextNote(String noteId) throws Exception;

    /**
     * 描述：ES分页查询未添加栏目的笔记
     *
     * @param userId     用户ID
     * @param queryParam 查询参数
     * @param noteIds    笔记id集合
     * @param columnId   专栏id
     * @param pageNum    页码
     * @param pageSize   页数
     * @return
     * @throws Exception
     */
    ESDataDto getNotColumnsNoteList(String userId, String queryParam, List<String> noteIds, String columnId, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：获取精品笔记（前三）
     *
     * @return 精品笔记（前三）
     * @throws Exception
     */
    ESDataDto getQualityNoteList() throws Exception;
}