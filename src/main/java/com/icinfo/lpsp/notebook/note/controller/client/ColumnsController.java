/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.controller.client;

import com.icinfo.framework.core.web.BaseController;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.ESColumnsDataDto;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IColumnsService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import com.icinfo.lpsp.notebook.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 描述: 专栏中心控制器.<br>
 *
 * @author framework generator
 * @date 2016年12月28日
 */
@Controller("clientColumnsController")
@RequestMapping("/client/columns")
public class ColumnsController extends BaseController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(ColumnsController.class);

    /**
     * 注入专栏服务
     */
    @Autowired
    private IColumnsService columnsService;

    /**
     * 注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 注入笔记服务
     */
    @Autowired
    private INoteService noteService;

    /**
     * 描述：进入专栏中心页
     *
     * @param model   模型
     * @param session 消息
     * @return 专栏中心页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        model.addAttribute("columnsDataDto", columnsService.getList(null, null, 0, 10));

        // 右侧精品笔记
        model.addAttribute("qualityNoteList", noteService.getQualityNoteList());

        if (user == null) {
            model.addAttribute("categoryList", categoryService.getCategoryList());
        } else {
            model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        }

        return "client/note/columnsList";
    }

    /**
     * 描述：获取专栏列表信息
     *
     * @param categoryId 类目id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 专栏列表信息
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public AjaxResponse<ESColumnsDataDto> queryColumns(@RequestParam(name = "categoryId", required = false) String categoryId,
                                                       @RequestParam(name = "queryParam", required = false) String queryParam,
                                                       @RequestParam(name = "pageNum", required = true) int pageNum,
                                                       @RequestParam(name = "pageSize", required = true) int pageSize) throws Exception {
        ESColumnsDataDto list;
        if (StringUtils.isBlank(categoryId)) {
            list = columnsService.getList(null, queryParam, pageNum, pageSize);
        } else {
            list = columnsService.getListByCategory(null, categoryId, queryParam, pageNum, pageSize);
        }

        return new AjaxResponse<>(list);
    }

    /**
     * 描述：根据类目id进入专栏列表页(面包屑)
     *
     * @param categoryId 类目id
     * @param model      模型
     * @param session    消息
     * @return 笔记列表页
     * @throws Exception
     */
    @RequestMapping(value = "/list/{categoryId}", method = RequestMethod.GET)
    public String listbyCategory(@PathVariable("categoryId") String categoryId, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        model.addAttribute("columnsDataDto", columnsService.getListByCategory(null, categoryId, null, 0, Constant.DEFAULT_PAGESIZE));
        model.addAttribute("categoryId", categoryId);

        // 右侧精品笔记
        model.addAttribute("qualityNoteList", noteService.getQualityNoteList());

        if (user == null) {
            model.addAttribute("categoryList", categoryService.getCategoryList());
        } else {
            model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        }
        return "client/note/columnsList";
    }
}