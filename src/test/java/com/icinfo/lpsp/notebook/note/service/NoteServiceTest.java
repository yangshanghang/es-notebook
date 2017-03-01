package com.icinfo.lpsp.notebook.note.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.HitsDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.model.Note;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * 笔记测试
 * Created by Administrator on 2016/11/16.
 */
public class NoteServiceTest extends SpringTxTestCase {
    @Autowired
    private INoteService noteService;

    /**
     * 描述：删除
     *
     * @throws Exception
     */
    @Test
//    @Ignore
    public void deleteNot() throws Exception {
        noteService.remove("7ac5d2c2d15c11e6a00a00188b839ae8");
    }

    /**
     * 描述：增加笔记
     *
     * @throws Exception
     */
    public void addNote() throws Exception {

    }

    /**
     * 测试新增修改笔记
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testAdd() throws Exception {
        NoteDto note = new NoteDto();
        note.setUserId("fd2b2b43d21611e69562fcaa14e16be9");
        note.setColumnOrderNumber(-1);
        note.setTitle("点赞测试");
        note.setSummary("点赞测试");
        note.setContent("点赞测试");
        note.setAuthor("黎力豪");
        note.setTypeId("1a55446cabc911e6a00a00188b839ae8");
        note.setCreateTime(new Date());
        note.setPraiseCount(10);
        note.setVisibleStatus("0");
        note.setOperateTime(note.getCreateTime());
        noteService.insertOrUpdate(note);
    }

    /**
     * 测试新增修改笔记
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testMoreAdd() throws Exception {
        for (int i = 0; i < 100; i++) {
            NoteDto note = new NoteDto();
            note.setTitle("VB分类速度测试");
            note.setSummary("分类速度测试摘要");
            note.setContent("分类速度测试内容。。。。。");
            note.setUserId("7b384978b50b11e6a00a00188b839ae8");
            note.setAuthor("系统管理员");
            note.setTypeId("2344897aac6e11e6a00a00188b839ae4");
            note.setVisibleStatus("0");
            note.setCommentCount(0);
            noteService.insertOrUpdate(note);
        }

    }

    /**
     * 测试获取专栏中的笔记列表
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetColumnsNoteList() throws Exception {
        String columnId = "cea3f5eace3c11e6a00a00188b839ae8";
        ESDataDto esDataDto = noteService.getColumnsNoteList(columnId, 1, 5);
        ObjectMapper mapper = new ObjectMapper();
        for (HitsDto hitsDto : esDataDto.getHits()) {
            System.out.println(mapper.writeValueAsString(hitsDto.get_source()));
        }
    }

    /**
     * 测试获取上一篇下一篇笔记
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetPrevAndNextNote() throws Exception {
        String noteId = "104b6446c68211e6a00a00188b839ae8";
        Map<String, Note> map = noteService.getPrevAndNextNote(noteId);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(map));
    }
}
