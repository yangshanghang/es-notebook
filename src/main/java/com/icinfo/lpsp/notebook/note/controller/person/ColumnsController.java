/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.ColumnsDto;
import com.icinfo.lpsp.notebook.note.dto.ESColumnsDataDto;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.HitsDto;
import com.icinfo.lpsp.notebook.note.model.Columns;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 我的专栏控制器.<br>
 *
 * @author framework generator
 * @date 2016年12月28日
 */
@Controller("personColumnsController")
@RequestMapping("/person/columns")
@SessionAttributes(Constant.SESSION_USER_INFO)
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
     * 注入笔记服务
     */
    @Autowired
    private INoteService noteService;

    /**
     * 注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 描述：进入我的专栏页
     *
     * @param user  用户信息
     * @param model 模型
     * @return 我的专栏页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        // 专栏列表
        model.addAttribute("columnsDataDto", columnsService.getList(user.getId(), null, 0, 10));
        // 类目列表
        model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        return "person/note/columnsList";
    }

    /**
     * 描述：获取我的专栏信息
     *
     * @param user       用户信息
     * @param categoryId 类目id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 我的专栏信息
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public AjaxResponse<ESColumnsDataDto> queryColumns(@ModelAttribute(Constant.SESSION_USER_INFO) User user,
                                                       @RequestParam(name = "categoryId", required = false) String categoryId,
                                                       @RequestParam(name = "queryParam", required = false) String queryParam,
                                                       @RequestParam(name = "pageNum", required = true) int pageNum,
                                                       @RequestParam(name = "pageSize", required = true) int pageSize) throws Exception {
        ESColumnsDataDto list;
        if (StringUtils.isBlank(categoryId)) {
            list = columnsService.getList(user.getId(), queryParam, pageNum, pageSize);
        } else {
            list = columnsService.getListByCategory(user.getId(), categoryId, queryParam, pageNum, pageSize);
        }
        return new AjaxResponse<>(list);
    }

    /**
     * 模型：进入新增专栏页面
     *
     * @param user  用户
     * @param model 模型
     * @return 新增专栏页面
     * @throws Exception
     */
    @RequestMapping(value = "/toadd", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        ESDataDto columnNoteList = new ESDataDto();
        // 添加非专利笔记列表
        model.addAttribute("notColumnNoteList", noteService.getNotColumnsNoteList(user.getId(), null, manageNoteIds(columnNoteList), "-1", 0, 10));
        // 类目列表
        model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        return "person/note/columnAddAndEdit";
    }

    /**
     * 描述：进入修改专栏页面
     *
     * @param user     用户
     * @param columnId 专栏id
     * @param model    模型
     * @return 修改专栏页面
     * @throws Exception
     */
    @RequestMapping(value = "/toedit/{columnId}", method = RequestMethod.GET)
    public String toEdit(@ModelAttribute(Constant.SESSION_USER_INFO) User user,
                         @PathVariable("columnId") String columnId,
                         Model model) throws Exception {
        // 1.根据id获取专栏信息
        Columns columns = columnsService.getColumn(columnId);
        // 2.若无结果则抛异常
        if (columns == null) {
            throw new BusinessException("专栏信息缺失！");
        }
        // 3.当前操作人非专栏作者，则抛异常
        if (!user.getId().equals(columns.getUserId())) {
            throw new BusinessException("您无权限操作！");
        }
        // 4.获取专栏内的笔记列表
        ESDataDto columnNoteList = noteService.getColumnsNoteList(columnId, 0, 100);
        //添加专栏笔记列表
        model.addAttribute("columnNoteList", columnNoteList);
        //添加专栏信息
        model.addAttribute("columns", columns);
        //添加非专栏笔记列表
        model.addAttribute("notColumnNoteList", noteService.getNotColumnsNoteList(user.getId(), "", manageNoteIds(columnNoteList), columnId, 0, 10));
        //添加类目列表
        model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        return "person/note/columnAddAndEdit";
    }

    /**
     * 描述：新增/修改专栏
     *
     * @param user       用户信息
     * @param columnsDto 专栏信息Dto id 专栏id
     *                   userId 用户id
     *                   author 作者
     *                   name:专栏名称
     *                   introduce：专栏介绍
     *                   visibleStatus：可见状态
     *                   categoryId：类目id
     * @return 返回结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/addoredit", method = RequestMethod.POST)
    public AjaxResponse<Boolean> addOrEdit(@ModelAttribute(Constant.SESSION_USER_INFO) User user,
                                           @RequestBody ColumnsDto columnsDto) throws Exception {
        // 1.新增/修改专栏
        return new AjaxResponse<>(columnsService.insertOrUpdate(columnsDto.getNoteList(), columnsDto));
    }

    /**
     * 描述：删除专栏
     *
     * @param columnId 专栏id
     * @return 返回结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResponse<Boolean> delete(@RequestParam(name = "columnId", required = true) String columnId,
                                        @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
        // 1.根据id获取专栏信息
        Columns columns = columnsService.getColumn(columnId);

        // 2.当前操作人非笔记作者，则返回错误信息
        if (columns != null && !user.getId().equals(columns.getUserId())) {
            return new AjaxResponse<>("1", "您无权限操作！");
        }
        //删除专栏
        columnsService.delete(columnId);
        return new AjaxResponse<>(true);
    }

    /**
     * 描述：获取无专栏的笔记列表（编辑专栏时查询使用）
     *
     * @param user       用户
     * @param noteIds    笔记id集合
     * @param queryParam 查询参数
     * @param columnId   专栏id
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 无专栏的笔记列表
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/querynotcolumnnote", method = RequestMethod.GET)
    public AjaxResponse<ESDataDto> queryNotColumnNote(@ModelAttribute(Constant.SESSION_USER_INFO) User user,
                                                      @RequestParam(value = "noteIds[]", required = false) List<String> noteIds,
                                                      @RequestParam(name = "queryParam", required = false) String queryParam,
                                                      @RequestParam(name = "columnId", required = false) String columnId,
                                                      @RequestParam(name = "pageNum", required = true) int pageNum,
                                                      @RequestParam(name = "pageSize", required = true) int pageSize) throws Exception {

        if (noteIds == null) {
            noteIds = new ArrayList<>();
        }
        return new AjaxResponse<>(noteService.getNotColumnsNoteList(user.getId(), queryParam, noteIds, columnId, pageNum, pageSize));
    }

    /**
     * 描述：处理笔记ID 列表
     *
     * @param columnNote 栏目笔记列表
     * @return id集合
     * @throws Exception
     */
    private List<String> manageNoteIds(ESDataDto columnNote) throws Exception {
        List<String> ids = new ArrayList<>();
        if (columnNote.getHits() != null) {
            for (HitsDto hitsDto : columnNote.getHits()) {
                ids.add(hitsDto.get_id());
            }
        }
        return ids;
    }
}