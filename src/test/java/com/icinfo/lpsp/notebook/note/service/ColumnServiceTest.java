package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.note.dto.ColumnsDto;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.model.Note;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：专栏测试类
 */
public class ColumnServiceTest extends SpringTxTestCase {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(ColumnServiceTest.class);

    @Autowired
    private IColumnsService columnsService;
    @Autowired
    private JestClient jestClient;

    @Autowired
    private INoteService noteService;

    /**
     * 描述：新增修改专栏测试
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testInsertOrUpdate() throws Exception {
        List<Note> list = new ArrayList<>();
        Note note = new Note();
        note.setId("5d722702d22411e6a00a00188b839ae8");
        list.add(note);
        Note note1 = new Note();
        note1.setId("e5cb17c6d22411e6a00a00188b839ae8");
        list.add(note1);
//        Note note2 = new Note();
//        note2.setId("561bfc6cd22411e6a00a00188b839ae8");
//        list.add(note2);


        ColumnsDto columnsDto = new ColumnsDto();
        columnsDto.setName("java测试专栏");
        columnsDto.setIntroduce("测试的专栏");
        columnsDto.setVisibleStatus("0");
        columnsDto.setCategoryId("300bc81cabc911e6a00a00188b839ae8");
        columnsDto.setAuthor("杨尚杭");
        columnsDto.setUserId("8fc9750ed15f11e6a00a00188b839ae8");
        columnsDto.setId("e6d71996d22711e6a00a00188b839ae8");
        columnsService.insertOrUpdate(list, columnsDto);
    }

    /**
     * 描述：删除专栏
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testDelete() throws Exception {
        columnsService.delete("68f8d7ecd2f611e6a00a00188b839ae8");
    }

    /**
     * 描述：根据专栏ID获取专栏
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetColumns() throws Exception {
        Columns columns = columnsService.getColumn("a7c4790cce6b11e6a00a00188b839ae8");
        logger.info(columns.toString());
    }


    /**
     * 描述：ES查询栏目笔记
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetColumnNote() throws Exception {
        noteService.getColumnsNoteList("f322046ece3e11e6a00a00188b839ae8", 0, 100);

    }


    /**
     * 描述：非栏目分页查询笔记
     */
    @Test
    @Ignore
    public void testGetNotColumnNote() throws Exception {

        List<String> list = new ArrayList<>();
        list.add("5f49def4c81411e6a00a00188b839ae8");
        //1.查询条件为空时
        noteService.getNotColumnsNoteList("263cce12c65911e6a00a00188b839ae8", "", null,null, 0, 10);
        //2.查询条件不为空时
//        columnsService.getNotColumnsNoteList("263cce12c65911e6a00a00188b839ae8", "", list, null, 0, 10);

    }

    /**
     * ES 删除栏目
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void delete() throws Exception {
        Delete.Builder builder = new Delete.Builder("a215d164ce6f11e6a00a00188b839ae8");
        Delete delete = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_COLUMNS).build();
        JestResult deleteRlt = jestClient.execute(delete);
    }
}
