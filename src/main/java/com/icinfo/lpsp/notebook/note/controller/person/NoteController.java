package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.common.util.HttpUtils;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.enums.EColumns;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IColumnsService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 我的笔记控制器 .<br>
 *
 * @author YangShangHang
 * @date 2016/11/10
 */
@Controller("personNoteController")
@RequestMapping("/person/note")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class NoteController extends BaseController {
    /**
     * 描述：注入笔记服务
     */
    @Autowired
    private INoteService noteService;

    /**
     * 描述：注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 描述：注入专栏服务
     */
    @Autowired
    private IColumnsService columnsService;

    /**
     * 描述：跳转到笔记列表页
     *
     * @param model 模型
     * @return 笔记列表页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        model.addAttribute("dataDto", noteService.getList(user.getId(), "", 0, 10));
        model.addAttribute("noteTypes", categoryService.getCategoryList(user.getId()));
        return "person/note/list";
    }

    /**
     * 描述：ajax获取笔记列表
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
    public AjaxResponse<ESDataDto> queryNotes(@ModelAttribute(Constant.SESSION_USER_INFO) User user,
                                              @RequestParam(name = "categoryId", required = false) String categoryId,
                                              @RequestParam(name = "queryParam", required = false) String queryParam,
                                              @RequestParam(value = "pageNum", required = true) int pageNum,
                                              @RequestParam(value = "pageSize", required = true) int pageSize) throws Exception {
        ESDataDto list;
        if (StringUtils.isBlank(categoryId)) {
            list = noteService.getList(user.getId(), queryParam, pageNum, pageSize);
        } else {
            list = noteService.getTypeQueryList(user.getId(), categoryId, queryParam, pageNum, pageSize);
        }

        return new AjaxResponse<>(list);
    }

    /**
     * 描述：进入笔记新增页
     *
     * @param user  用户
     * @param model 模型
     * @return 笔记新增页
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        return "person/note/addAndEdit";
    }

    /**
     * 描述：进入笔记修改页
     *
     * @param indexId 索引id(笔记id)
     * @param user    用户
     * @param model   模型
     * @return 笔记修改页
     * @throws Exception
     */
    @RequestMapping(value = "/edit/{indexId}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("indexId") String indexId, @ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        // 1.根据索引id查询es
        NoteDto note = noteService.get(indexId);
        // 2.若无结果，则抛异常
        if (note == null) {
            throw new BusinessException("笔记信息缺失！");
        }

        // 3.当前操作人非笔记作者，则抛异常
        if (!user.getId().equals(note.getUserId())) {
            throw new BusinessException("您无权限操作！");
        }

        model.addAttribute("note", note);
        model.addAttribute("indexId", indexId);
        model.addAttribute("categoryList", categoryService.getCategoryList(user.getId()));
        return "person/note/addAndEdit";
    }

    /**
     * 新增修改笔记
     *
     * @param note 笔记信息
     * @return true:新增修改成功
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "addoredit", method = RequestMethod.POST)
    public AjaxResponse<String> addOrEdit(NoteDto note) throws Exception {
        // 1.若为修改笔记
        if (!StringUtils.isBlank(note.getId())) {
            // 2.根据索引id查询es
            NoteDto esNote = noteService.get(note.getId());

            // 3.若笔记在某专栏里面
            if (!esNote.getColumnId().equals("-1")) {
                Columns column = columnsService.getColumn(esNote.getColumnId());
                // 4.专栏为公开状态，则笔记可见状态及类目不可修改
                if (column != null && column.getVisibleStatus().equals(EColumns.VISIBLE_STATUS_PUBLIC.getValue())) {
                    if (!esNote.getVisibleStatus().equals(note.getVisibleStatus()) || !esNote.getTypeId().equals(note.getTypeId())) {
                        return new AjaxResponse<>("error", "该笔记在专栏内，无法修改可见状态及类目，请将它移出专栏后，再进行此操作！");
                    }
                }
                // 5.专栏为不可见状态，则笔记类目不可修改
                else if (column != null && column.getVisibleStatus().equals(EColumns.VISIBLE_STATUS_PERSON.getValue())) {
                    if (!esNote.getTypeId().equals(note.getTypeId())) {
                        return new AjaxResponse<>("error", "该笔记在专栏内，无法修改可见状态及类目，请将它移出专栏后，再进行此操作！");
                    }

                }
            }
        }
        return new AjaxResponse<>(noteService.insertOrUpdate(note));
    }

    /**
     * 描述：删除笔记
     *
     * @param indexId 索引id
     * @return 删除结果  成功：true，失败：false
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> delete(@RequestParam(name = "indexId", required = true) String indexId,
                                        @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
        // 1.根据索引id查询es
        NoteDto note = noteService.get(indexId);

        // 2.当前操作人非笔记作者，则返回错误信息
        if (note != null && !user.getId().equals(note.getUserId())) {
            return new AjaxResponse<>("1", "您无权限操作！");
        }
        noteService.remove(indexId);
        return new AjaxResponse<>(true);
    }

    /**
     * 描述：心跳响应
     *
     * @param response 响应
     * @throws Exception
     */
    @RequestMapping("/heartbeat")
    public void heartbeat(HttpServletResponse response) throws Exception {
        HttpUtils.writeError(response, "ok", HttpStatus.OK.value());
    }
}
