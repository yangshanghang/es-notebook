package com.icinfo.lpsp.notebook.note.controller.person;

import com.icinfo.framework.mybatis.pagehelper.PageInfo;
import com.icinfo.lpsp.notebook.common.bean.AjaxResponse;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.controller.BaseController;
import com.icinfo.lpsp.notebook.note.dto.CommentDto;
import com.icinfo.lpsp.notebook.note.model.Comment;
import com.icinfo.lpsp.notebook.note.service.ICommentService;
import com.icinfo.lpsp.notebook.note.service.impl.NoteServiceImpl;
import com.icinfo.lpsp.notebook.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 描述: 评论控制器 .<br>
 *
 * @author YangShangHang
 * @date 2016/11/24
 */
@Controller("personCommentController")
@RequestMapping("/person/comment")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class CommentController extends BaseController {
    /**
     * 描述：注入评论服务
     */
    @Autowired
    private ICommentService commentService;

    /**
     * 描述：注入笔记服务
     */
    @Autowired
    private NoteServiceImpl noteService;

    /**
     * 描述：发表评论
     *
     * @param commentDto 评论
     * @return 发表评论结果
     * @throws Exception
     */
    @RequestMapping(value = "/makecomments", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> makeComments(CommentDto commentDto, HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        // 1.用户session失效
        if (user == null) {
            return new AjaxResponse<>("1", "您长时间未登录，请重新登录！");
        }
        // 2.根据id获取笔记，若为空，返回错误信息
        if (noteService.get(commentDto.getNoteId()) == null) {
            return new AjaxResponse<>("2", "该笔记已删除！");
        }
        // 3.保存发表的评论,返回评论DTO
        commentService.save(user.getId(), commentDto);
        return new AjaxResponse<>(true);
    }

    /**
     * 描述：加载更多评论
     *
     * @param indexId  笔记id
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 评论分页列表
     * @throws Exception
     */
    @RequestMapping(value = "/getmorecomments", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<PageInfo<CommentDto>> getMoreComments(@RequestParam(value = "indexId", required = true) String indexId,
                                                              @RequestParam(value = "pageNum", required = true) int pageNum,
                                                              @RequestParam(value = "pageSize", required = true) int pageSize) throws Exception {
        PageInfo<CommentDto> pageInfo = commentService.getCommentList(indexId, pageNum, pageSize);
        return new AjaxResponse<>(pageInfo);
    }

    /**
     * 描述：删除评论
     *
     * @param commentId 评论id
     * @return 删除评论结果
     * @throws Exception
     */
    @RequestMapping(value = "/deletecomment", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> deleteComment(@RequestParam(value = "commentId", required = true) String commentId,
                                               @RequestParam(value = "noteId", required = true) String noteId,
                                               HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constant.SESSION_USER_INFO);
        // 1.用户session失效
        if (user == null) {
            return new AjaxResponse<>("1", "您长时间未登录，请重新登录！");
        }

        // 2.笔记已删除
        if (noteService.get(noteId) == null) {
            return new AjaxResponse<>("2", "该笔记已删除！");
        }

        // 3.根据评论id获取评论
        Comment comment = commentService.getComment(commentId);

        // 4.判断操作人是否是评论发表人
        if( comment != null && !user.getId().equals(comment.getUserId())){
            return new AjaxResponse<>("3", "您无权限操作！");
        }

        // 5.删除评论
        commentService.removeComment(commentId);
        return new AjaxResponse<>(true);
    }
}
