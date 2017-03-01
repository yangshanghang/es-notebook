package com.icinfo.lpsp.notebook.system;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.common.util.DateUtils;
import com.icinfo.lpsp.notebook.note.model.MeetingBook;
import com.icinfo.lpsp.notebook.note.service.IMeetingBookService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述: 会议室预定测试类 .<br>
 *
 * @author YangShangHang
 * @date 2017/2/9
 */
public class MeetingBookServiceTest extends SpringTxTestCase {
    @Autowired
    private IMeetingBookService meetingBookService;

    /**
     * 新增会议室预定 测试
     *
     * @throws Exception
     */
    @Test
//    @Ignore
    public void addTest() throws Exception {
        MeetingBook meetingBook = new MeetingBook();
        meetingBook.setUserId("123456");
        meetingBook.setMeetingType("1");
        meetingBook.setTheme("项目会议");
        meetingBook.setBooker("小王");
        meetingBook.setDepartment("品控部");
        meetingBook.setStartTime(DateUtils.stringToDate("2017-02-13 9:30", "yyyy-MM-dd HH:mm"));
        meetingBook.setEndTime(DateUtils.stringToDate("2017-02-13 10:30", "yyyy-MM-dd HH:mm"));
        meetingBookService.save(meetingBook);
    }

    /**
     * 获取会议室预定列表测试
     *
     * @throws Exception
     */
    @Test
//    @Ignore
    public void getListTest() throws Exception {
        String meetingType = "2";
        List<MeetingBook> list = meetingBookService.getList(meetingType);
        System.out.println(list);
    }


    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static void main(String[] args) {
        System.out.println(getWeekOfDate(new Date()));
    }
}