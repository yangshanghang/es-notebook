package com.icinfo.lpsp.notebook.note.controller.client;

import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.CommentDto;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.enums.ENote;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.service.*;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 描述: 笔记检索控制器 .<br>
 *
 * @author YangShangHang
 * @date 2016/11/10
 */
@Controller("clientNoteController")
@RequestMapping("/client/note")
public class NoteController extends BaseController {
    /**
     * 描述：注入评论服务
     */
    @Autowired
    private ICommentService commentService;

    /**
     * 描述：注入笔记类型服务
     */
    @Autowired
    private INoteService noteService;

    /**
     * 描述：注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 描述：收藏服务
     */
    @Autowired
    private IMyFavoriteService myFavoriteService;


    /**
     * 注入栏目mapper
     */
    @Autowired
    private IColumnsService columnsService;

    /**
     * 描述：进入笔记列表页
     *
     * @param queryParam 查询参数
     * @param model      模型
     * @param session    session
     * @return 笔记列表页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(name = "queryParam", required = false) String queryParam, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        model.addAttribute("dataDto", noteService.getList(null, queryParam, 0, Constant.DEFAULT_PAGESIZE));
        model.addAttribute("queryParam", queryParam);

        // 右侧精品笔记
        model.addAttribute("qualityNoteList", noteService.getQualityNoteList());

        if (user == null) {
            model.addAttribute("noteTypes", categoryService.getCategoryList());
        } else {
            model.addAttribute("noteTypes", categoryService.getCategoryList(user.getId()));
        }

        return "client/note/list";
    }

    /**
     * 描述：异步获取笔记列表
     *
     * @param categoryId 类目id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 笔记列表
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public AjaxResponse<ESDataDto> queryNotes(@RequestParam(name = "categoryId", required = false) String categoryId, @RequestParam(name = "queryParam", required = false) String queryParam, int pageNum, int pageSize) throws Exception {
        ESDataDto list;
        if (StringUtils.isBlank(categoryId)) {
            list = noteService.getList(null, queryParam, pageNum, pageSize);
        } else {
            list = noteService.getTypeQueryList(null, categoryId, queryParam, pageNum, pageSize);
        }

        return new AjaxResponse<>(list);
    }

    /**
     * 描述：进入笔记详情页
     *
     * @param indexId 笔记id
     * @param model   模型
     * @return 笔记详情页
     * @throws Exception
     */
    @RequestMapping(value = "/detail/{indexId}", method = RequestMethod.GET)
    public String detail(@PathVariable("indexId") String indexId, HttpSession session, Model model) throws Exception {
        // 1.根据索引id查询笔记
        NoteDto noteDto = noteService.get(indexId);
        // 2.若es中无结果，则抛出异常
        if (noteDto == null) {
            throw new BusinessException("笔记信息缺失！");
        }
        model.addAttribute("note", noteDto);
        model.addAttribute("indexId", indexId);

        // 右侧精品笔记
        model.addAttribute("qualityNoteList", noteService.getQualityNoteList());

        // 3.评论分页显示
        PageInfo<CommentDto> pageInfo = commentService.getCommentList(indexId, Constant.DEFAULT_PAGENUM, Constant.DEFAULT_PAGESIZE);
        model.addAttribute("comments", pageInfo.getList());
        model.addAttribute("hasNextPage", pageInfo.isHasNextPage());
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        if (user != null) {
            // 4.model添加是否已收藏此笔记
            model.addAttribute("isFavorite", myFavoriteService.isFavorite(user.getId(), indexId));
        }

        //5.若为专栏下的笔记，获取目录、上一篇下一篇笔记
        Columns columns = columnsService.getColumn(noteDto.getColumnId());
        model.addAttribute("columns", columns);
        if (columns != null) {
            model.addAttribute("prevNext", noteService.getPrevAndNextNote(noteDto.getId()));
            model.addAttribute("noteList", noteService.getColumnsNoteList(noteDto.getColumnId(), 0, 10));
        }
        return "client/note/detail";
    }

    /**
     * 描述：用户点赞
     *
     * @param indexId 笔记id
     * @return 点赞结果
     * @throws Exception
     */
    @RequestMapping(value = "/pushpraise", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> pushPraise(@RequestParam(name = "indexId", required = true) String indexId) throws Exception {
        noteService.pushPraise(indexId);
        return new AjaxResponse<>(true);
    }

    /**
     * 描述：进入专栏笔记列表
     *
     * @param columnId 专栏id
     * @param model    模型
     * @return 专栏笔记列表
     * @throws Exception
     */
    @RequestMapping(value = "/tocolumnnotelist/{columnId}", method = RequestMethod.GET)
    public String toColumnNoteList(@PathVariable("columnId") String columnId, Model model) throws Exception {
        // 1.根据id查询专栏
        Columns columns = columnsService.getColumn(columnId);
        // 2.若无结果，则抛出异常
        if (columns == null) {
            throw new BusinessException("专栏信息缺失！");
        }

        // 右侧精品笔记
        model.addAttribute("qualityNoteList", noteService.getQualityNoteList());

        model.addAttribute("columns", columns);
        model.addAttribute("category", categoryService.get(columns.getCategoryId()));
        model.addAttribute("noteList", noteService.getColumnsNoteList(columnId, 0, 100));
        return "client/note/columnNoteList";
    }

    /**
     * 描述：获取专栏笔记列表信息
     *
     * @param columnId 专栏id
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 专栏笔记列表信息
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/readcolumnnotelist", method = RequestMethod.GET)
    public AjaxResponse<ESDataDto> readColumnNoteList(@RequestParam(name = "columnId", required = true) String columnId,
                                                      @RequestParam(name = "pageNum", required = true) int pageNum,
                                                      @RequestParam(name = "pageSize", required = true) int pageSize) throws Exception {
        return new AjaxResponse<>(noteService.getColumnsNoteList(columnId, pageNum, pageSize));
    }
}
