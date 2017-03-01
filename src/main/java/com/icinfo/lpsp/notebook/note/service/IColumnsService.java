/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.service.BaseService;
import com.icinfo.lpsp.notebook.note.dto.ColumnsDto;
import com.icinfo.lpsp.notebook.note.dto.ESColumnsDataDto;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.model.Note;

import java.util.List;

/**
 * 描述: 专栏表 NBOOK_COLUMNS 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年12月28日
 */
public interface IColumnsService extends BaseService {

    /**
     * 描述：ES分页查询专栏
     *
     * @param userId     用户Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 专栏列表
     * @throws Exception
     */
    ESColumnsDataDto getList(String userId, String queryParam, int pageNum, int pageSize) throws Exception;

    /**
     * 描述：ES根据类目分页查询专栏
     *
     * @param userId     用户id
     * @param categoryId 类目id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 专栏列表
     * @throws Exception
     */
    ESColumnsDataDto getListByCategory(String userId, String categoryId, String queryParam, int pageNum, int pageSize) throws Exception;


    /**
     * 描述：新增修改专栏
     *
     * @param list       专栏笔记列表
     * @param columnsDto 专栏DTO对象
     * @return
     * @throws Exception
     */
    boolean insertOrUpdate(List<Note> list, ColumnsDto columnsDto) throws Exception;


    /**
     * 描述:删除专栏
     *
     * @param columnId 专栏Id
     * @return
     * @throws Exception
     */
    void delete(String columnId) throws Exception;

    /**
     * 获取栏目详情
     *
     * @param columnId 栏目id
     * @return 栏目详情
     * @throws Exception
     */
    Columns getColumn(String columnId) throws Exception;

    /**
     * 描述：根据栏目id 修改笔记数量
     *
     * @param columnId 栏目id
     * @throws Exception
     */
    void updateNoteCount(String columnId) throws Exception;
}