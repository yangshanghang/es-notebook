package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.model.MyFavorite;
import com.icinfo.lpsp.notebook.note.model.Note;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 我的收藏测试类
 */
public class MyFavoriteTest extends SpringTxTestCase {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(MyFavoriteTest.class);

    /**
     * 描述：注入
     */
    @Autowired
    private IMyFavoriteService myFavoriteService;


    /**
     * 描述：保存我的收藏
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testSave() throws Exception {
        myFavoriteService.save("dc494c5ab11c11e6a00a00188b839ae8", "3d3a4e52ad2a11e6a00a00188b839ae8");

    }

    /**
     * 描述：删除我的收藏
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testRemove() throws Exception {
        myFavoriteService.remove("dc494c5ab11c11e6a00a00188b839ae8", "3d3a4e52ad2a11e6a00a00188b839ae8");
    }


    /**
     * 描述：获取我的收藏笔记列表
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void getMyFavoriteList() throws Exception {
        PageInfo<NoteDto> page = myFavoriteService.getMyFavoriteList("dc494c5ab11c11e6a00a00188b839ae8", "","batis", 1, 10);
        for (Note note : page.getList()) {
            logger.info(note.getTitle());
        }
    }

}
