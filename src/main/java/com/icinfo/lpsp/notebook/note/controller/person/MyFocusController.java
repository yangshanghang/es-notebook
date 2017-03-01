package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.note.model.MyFocus;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IMyFocusService;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的关注 控制器
 */
@Controller("personMyFocusController")
@RequestMapping("/person/myfocus")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class MyFocusController extends BaseController {
    /**
     * 描述：注入笔记类型服务
     */
    @Autowired
    private ICategoryService categoryService;

    /**
     * 注入我的关注服务
     */
    @Autowired
    private IMyFocusService focusService;

    /**
     * 描述：跳转到分类关注页
     *
     * @param user  用户对象
     * @param model 模型
     * @return 分类关注页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String myFocus(@ModelAttribute(Constant.SESSION_USER_INFO) User user, Model model) throws Exception {
        model.addAttribute("map", categoryService.getCategoryData(user.getId()));
        return "person/note/myFocus";
    }

    /**
     * 描述：保存我的关注
     *
     * @param myFocusList 我的关注列表
     * @return 保存我的关注结果
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResponse<Boolean> add(@ModelAttribute(Constant.SESSION_USER_INFO) User user, @RequestBody List<MyFocus> myFocusList) throws Exception {
        return new AjaxResponse<>(focusService.saveList(user.getId(), myFocusList));
    }
}
