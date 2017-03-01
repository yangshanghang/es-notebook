package com.icinfo.lpsp.notebook.upload.controller.client;

import com.icinfo.lpsp.notebook.common.ueditor.ActionEnter;
import com.icinfo.lpsp.notebook.upload.dto.UEditorUploadRltDto;
import com.icinfo.lpsp.notebook.upload.service.IUEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * UEditor 编辑器配置
 * <p>
 * Created by wangxiao on 2016/11/17.
 */
@Controller("clientUeditorController")
@RequestMapping("/client/ueditor")
public class UEditorController {

    /**
     * 描述：注入文件上传服务
     */
    @Autowired
    private IUEditorService ueditorService;

    /**
     * 初始化配置
     *
     * @param request  Http请求
     * @param response Http响应
     */
    @RequestMapping("/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param upfile 图片文件
     * @return 上传结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImage", produces = "application/json")
    public UEditorUploadRltDto uploadImage(@RequestParam(value = "upfile", required = true) MultipartFile[] upfile) throws Exception {
        return ueditorService.uploadImage(upfile[0]);
    }

    /**
     * 上传涂鸦
     *
     * @param upfile 涂鸦文件
     * @return 上传结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadScrawl", produces = "application/json")
    public UEditorUploadRltDto uploadScrawl(@RequestParam(value = "upfile", required = true) String upfile) throws Exception {
        return ueditorService.uploadScrawlImage(upfile);
    }

    /**
     * 上传远程抓图
     * @param sources 远程抓图地址列表
     * @return 上传结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadCatchImage", produces = "application/json")
    public UEditorUploadRltDto uploadCatchImage(@RequestParam(name="source[]", required = true)List<String> sources) throws Exception{
        UEditorUploadRltDto result = new UEditorUploadRltDto();
        result.setState(UEditorUploadRltDto.SUCCESS_STATE);
        return result;
    }

    /**
     * 上传附件
     *
     * @param upfile 附件文件
     * @return 上传结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadAttachment", produces = "application/json")
    public UEditorUploadRltDto uploadAttachment(@RequestParam(value = "upfile", required = true) MultipartFile[] upfile) throws Exception {
        return ueditorService.uploadAttachment(upfile[0]);
    }
}
