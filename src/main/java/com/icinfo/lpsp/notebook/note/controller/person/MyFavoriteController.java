package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IMyFavoriteService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：我的收藏控制器
 */
@Controller("personMyFavoriteController")
@RequestMapping("/person/myfavorite")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class MyFavoriteController extends BaseController {
    /**
     * 描述：service注入
     */
    @Autowired
    private IMyFavoriteService myFavoriteService;

    /**
     * 描述：笔记service 注入
     */
    @Autowired
    private INoteService noteService;

    /**
     * 描述：注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 描述：跳转到收藏页
     *
     * @param user  用户对象
     * @param model 模型
     * @return 我的收藏页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        model.addAttribute("pageInfo", myFavoriteService.getMyFavoriteList(user.getId(), "", "", 1, 10));
        model.addAttribute("noteTypes", categoryService.getCategoryList(user.getId()));
        return "person/note/myFavorite";
    }

    /**
     * 描述：ajax 获取收藏页列表
     *
     * @param categoryId     类别ID
     * @param queryParamLike 查询参数
     * @param pageNum        页码
     * @param pageSize       页数
     * @param user           用户对象
     * @return 我的收藏列表
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/querymyfavorite", method = RequestMethod.GET)
    public AjaxResponse<PageInfo<NoteDto>> queryMyFavorite(@RequestParam(value = "categoryId", required = false) String categoryId,
                                                           @RequestParam(value = "queryParamLike", required = false) String queryParamLike,
                                                           @RequestParam(value = "pageNum", required = true) int pageNum,
                                                           @RequestParam(value = "pageSize", required = true) int pageSize,
                                                           @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {

        return new AjaxResponse<>(myFavoriteService.getMyFavoriteList(user.getId(), categoryId, queryParamLike, pageNum, pageSize));
    }

    /**
     * 描述：收藏笔记
     *
     * @param noteId 笔记id
     * @param user   用户对象
     * @return 收藏笔记结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResponse<Boolean> add(@RequestParam(value = "noteId", required = true) String noteId,
                                     @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
        if (noteService.getNote(noteId) == null) {
            return new AjaxResponse<>(false);
        }
        return new AjaxResponse<>(myFavoriteService.save(user.getId(), noteId));
    }

    /**
     * 描述：删除收藏的笔记
     *
     * @param noteId 笔记id
     * @param user   用户对象
     * @return 删除结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public AjaxResponse<Boolean> delete(@RequestParam(value = "noteId", required = true) String noteId,
                                        @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {

        return new AjaxResponse<>(myFavoriteService.remove(user.getId(), noteId));
    }

    /**
     * 描述：判断用户是否已收藏此笔记
     *
     * @param noteId 笔记id
     * @param userId 用户id
     * @return true：已收藏 false：未收藏
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/isFavorite", method = RequestMethod.GET)
    public AjaxResponse<Boolean> isFavorite(@RequestParam(value = "noteId", required = true) String noteId,
                                            String userId) throws Exception {
        //1.判断用户是否已经登录
        if (StringUtils.isEmpty(userId)) {
            return new AjaxResponse<>(false);
        }
        return new AjaxResponse<>(myFavoriteService.isFavorite(userId, noteId));
    }
}
