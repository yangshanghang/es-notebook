package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.note.model.MyFocus;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分类关注 测试
 */
public class MyFocusServiceTest extends SpringTxTestCase {

    @Autowired
    private IMyFocusService myFocusService;

    /**
     * 描述：保存我的类别 列表
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void saveList() throws Exception {
        MyFocus myFocus = new MyFocus();
        myFocus.setCategoryId("1a55446cabc911e6a00a00188b839ae8");
        myFocus.setCreateTime(new Date());
        myFocus.setOperateTime(myFocus.getCreateTime());
        myFocus.setOrderNumber(1);
        List<MyFocus> myFocusList = new ArrayList<>();
        myFocusList.add(myFocus);
        myFocusService.saveList("dc494c5ab11c11e6a00a00188b839ae8", myFocusList);

    }
}
