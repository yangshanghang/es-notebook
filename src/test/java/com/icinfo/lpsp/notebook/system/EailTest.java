package com.icinfo.lpsp.notebook.system;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.common.bean.Mail;
import com.icinfo.lpsp.notebook.common.util.MailUtil;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/22.
 */
public class EailTest extends SpringTxTestCase {


    /**
     * 描述：测试邮箱发送
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testSendEmail() throws Exception {
        Mail mail = new Mail();
        //设置收件人邮箱
        mail.setReceiver("740641026@qq.com");
        //设置收件人名称
        mail.setReceiverName("lilihao");
        //设置主体
        mail.setSubject("测试一下");
        //设置内容 其包可以包括html
        mail.setMessage("<a href='wwww.50ldsh.com'>跳转博客</a>");

        //发送邮件
        Boolean send = new MailUtil().send(mail);
        System.out.println(send ? "发送成功" : "发送失败");
//        Mail mail = new Mail();
//        // 设置邮件服务器
//        mail.setHost("smtp.exmail.qq.com");
//        // 发件人邮件地址
//        mail.setSender("chenhao@kezhanbang.cn");
//        // 发件人名称
//        mail.setName("Java.小学生");
//        // 登录账号,一般都是和邮箱名一样吧
//        mail.setUsername("chenhao@kezhanbang.cn");
//        // 发件人邮箱的登录密码
//        mail.setPassword("xxxxxxxx");
//        // 接收人
//        mail.setReceiver("huntertochen@163.com");
//        mail.setReceiverName("我要女票");
//        mail.setSubject("万里长征最后一步测试");
//        String html = "<!DOCTYPE html>";
//        html += "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
//        html += "<title>Insert title here</title>";
//        html += "</head><body>";
//        html += "<div style=\"width:600px;height:400px;margin:50px auto;\">";
//        html += "<h1>我来看看邮件是否发送成功呢</h1><br/><br/>";
//        html += "<p>下面是通过该协议可以创建一个指向电子邮件地址的超级链接，通过该链接可以在Internet中发送电子邮件</p><br/>";
//        html += "<a href=\"mailto:huntereagle@foxmail.com?subject=test&cc=huntertochen@163.com&body=use mailto sample\">send mail</a>";
//        html += "</div>";
//        html += "</body></html>";
//        mail.setMessage(html);

        new MailUtil().send(mail);
    }

}
