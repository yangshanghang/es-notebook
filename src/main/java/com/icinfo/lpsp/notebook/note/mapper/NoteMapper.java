/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年11月15日
 * @version 2.0
 */
package com.icinfo.lpsp.notebook.note.mapper;
import com.icinfo.framework.mybatis.mapper.common.Mapper;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.model.Note;
import java.util.List;
import java.util.Map;

public interface NoteMapper extends Mapper<Note> {

    /**
     * 描述：获取我的收藏笔记列表
     *
     * @return
     * @throws Exception
     */
    List<NoteDto> getMyFavoriteNoteList(Map<String, String> map) throws Exception;

    /**
     * 描述：删除笔记
     *
     * @param noteId 笔记id
     * @throws Exception
     */
    void deleteNote(String noteId) throws Exception;
}