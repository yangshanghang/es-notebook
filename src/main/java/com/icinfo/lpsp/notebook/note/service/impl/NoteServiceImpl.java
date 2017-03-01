/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.Page;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.lpsp.notebook.common.constant.ConfigConstant;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.RegexpUtils;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.ESDataDto;
import com.icinfo.lpsp.notebook.note.dto.HitsDto;
import com.icinfo.lpsp.notebook.note.dto.NoteDto;
import com.icinfo.lpsp.notebook.note.enums.ENote;
import com.icinfo.lpsp.notebook.note.mapper.NoteMapper;
import com.icinfo.lpsp.notebook.note.model.Category;
import com.icinfo.lpsp.notebook.note.model.Comment;
import com.icinfo.lpsp.notebook.note.model.Note;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IColumnsService;
import com.icinfo.lpsp.notebook.note.service.ICommentService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
import com.icinfo.lpsp.notebook.upload.service.IUEditorService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * 描述:  NBOOK_NOTE 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年11月15日
 */
@Service
public class NoteServiceImpl extends MyBatisServiceSupport implements INoteService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    /**
     * 注入笔记Mapper
     */
    @Autowired
    private NoteMapper noteMapper;

    /**
     * 注入类目服务
     */
    @Autowired
    private ICategoryService categorySerive;

    @Autowired
    private JestClient jestClient;

    /**
     * 注入评论服务
     */
    @Autowired
    private ICommentService commentService;

    /**
     * 注入ueditor服务
     */
    @Autowired
    private IUEditorService ueditorService;

    /**
     * 注入栏目服务
     */
    @Autowired
    private IColumnsService columnsService;

    /**
     * 描述：分页查询笔记
     *
     * @param userId     用户Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 笔记列表
     * @throws Exception
     */
    @Override
    public ESDataDto getList(String userId, String queryParam, int pageNum, int pageSize) throws Exception {
        SearchSourceBuilder searchSourceBuilder;
        // author为空表示未登录
        if (StringUtils.isBlank(userId)) {
            //1.判断参数是否为空
            if (StringUtils.isBlank(queryParam)) {
                //为空查询所有
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).postFilter(multiMatchQuery(ENote.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).sort("operateTime", SortOrder.DESC);
            } else {
                //不为空查询指定参数
                QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(multiMatchQuery(queryParam, "title").boost(1000))
                        .should(multiMatchQuery(queryParam, "summary").boost(100))
                        .should(multiMatchQuery(queryParam, "content").boost(10))
                        .should(multiMatchQuery(queryParam, "category.name").boost(2))
                        .should(multiMatchQuery(queryParam, "author"));
                searchSourceBuilder = new SearchSourceBuilder().query(queryBuilder).postFilter(multiMatchQuery(ENote.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus"));
            }
        }
        // 用户已登录
        else {
            //1.判断参数是否为空
            if (StringUtils.isBlank(queryParam)) {
                //为空查询所有
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).sort("operateTime", SortOrder.DESC).postFilter(multiMatchQuery(userId, "userId"));
            } else {
                //不为空查询指定参数
                QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(multiMatchQuery(queryParam, "title").boost(1000))
                        .should(multiMatchQuery(queryParam, "summary").boost(100))
                        .should(multiMatchQuery(queryParam, "content").boost(10))
                        .should(multiMatchQuery(queryParam, "category.name").boost(2))
                        .should(multiMatchQuery(queryParam, "author"));
                searchSourceBuilder = new SearchSourceBuilder().postFilter(multiMatchQuery(userId, "userId")).query(queryBuilder);
            }
        }
        //2.查询
        return query(searchSourceBuilder, pageNum, pageSize);
    }

    /**
     * 描述：根据类型查询
     *
     * @param userId     用户Id
     * @param categoryId 类型Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 笔记列表
     * @throws Exception
     */
    @Override
    public ESDataDto getTypeQueryList(String userId, String categoryId, String queryParam, int pageNum, int pageSize) throws Exception {
        SearchSourceBuilder searchSourceBuilder;
        // 用户未登录
        if (StringUtils.isBlank(userId)) {
            if (StringUtils.isBlank(queryParam)) {
                searchSourceBuilder = new SearchSourceBuilder().query(multiMatchQuery(categoryId, "category.id")).postFilter(multiMatchQuery(ENote.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.boolQuery()
                        .should(multiMatchQuery(queryParam, "title").boost(1000))
                        .should(multiMatchQuery(queryParam, "summary").boost(100))
                        .should(multiMatchQuery(queryParam, "content").boost(10))
                        .should(multiMatchQuery(queryParam, "category.name").boost(2))
                        .should(multiMatchQuery(queryParam, "author")))
                        .postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(ENote.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).must(multiMatchQuery(categoryId, "category.id")));
            }
        }
        // 用户已登录
        else {
            if (StringUtils.isBlank(queryParam)) {
                searchSourceBuilder = new SearchSourceBuilder().query(multiMatchQuery(categoryId, "category.id")).postFilter(multiMatchQuery(userId, "userId")).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder()
                        .postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(categoryId, "category.id")).must(multiMatchQuery(userId, "userId")))
                        .query(QueryBuilders.boolQuery()
                                .should(multiMatchQuery(queryParam, "title").boost(1000))
                                .should(multiMatchQuery(queryParam, "summary").boost(100))
                                .should(multiMatchQuery(queryParam, "content").boost(10))
                                .should(multiMatchQuery(queryParam, "category.name").boost(2))
                                .should(multiMatchQuery(queryParam, "author")));
            }

        }
        return query(searchSourceBuilder, pageNum, pageSize);
    }

    /**
     * 描述：ES根据索引id 查找笔记
     *
     * @param indexId 索引ID
     * @return 笔记详情
     * @throws Exception
     */
    @Override
    public NoteDto get(String indexId) throws Exception {
        // 1.创建Get对象
        Get get = new Get.Builder(Constant.INDEX_NAME_NOTE, indexId).type(Constant.INDEX_TYPE_NOTE).build();
        JestResult result = jestClient.execute(get);
        // 2.如果查询失败，返回null
        if (!result.isSucceeded()) {
            return null;
        }
        // 3.查询结果转换成对象
        NoteDto noteDto = result.getSourceAsObject(NoteDto.class);
        return noteDto == null ? null : noteDto;
    }

    /**
     * 描述：根据笔记id 查找笔记
     *
     * @param noteId 笔记id
     * @return 笔记id
     * @throws Exception
     */
    @Override
    public Note getNote(String noteId) throws Exception {
        return noteMapper.selectByPrimaryKey(noteId);
    }

    /**
     * 描述：ES查询
     *
     * @return ES查询结果
     * @throws Exception
     */
    private ESDataDto query(SearchSourceBuilder searchSourceBuilder, int pageNum, int pageSize) throws Exception {
        //1.创建查询对象
        Search search = new Search.Builder(searchSourceBuilder.highlight(new HighlightBuilder()
                .field("title").field("summary").field("category.name").field("author")
                .preTags("<B class = 'es-highlight'>")
                .postTags("</B>")).toString())
                .addIndex(Constant.INDEX_NAME_NOTE)
                .addType(Constant.INDEX_TYPE_NOTE)
                .setParameter("from", pageNum * pageSize)
                .setParameter("size", pageSize)
                .build();
        //2.执行查询返回结果
        JestResult result = jestClient.execute(search);

        ESDataDto dataDto = new ESDataDto();
        //3.若查询结果是成功将结果转换对象
        if (result.isSucceeded()) {
            String str = result.getJsonObject().get("hits").toString();
            ObjectMapper object = new ObjectMapper();
            dataDto = object.readValue(str, ESDataDto.class);
            dataDto.setTook(result.getJsonObject().get("took").getAsLong());
            //设置高亮文本
            for (HitsDto hitsDto : dataDto.getHits()) {
                if (hitsDto.getHighlight() != null) {
                    if (hitsDto.getHighlight().getTitle() != null) {
                        hitsDto.get_source().setTitle(hitsDto.getHighlight().getTitle()[0]);
                    }
                    if (hitsDto.getHighlight().getSummary() != null) {
                        hitsDto.get_source().setSummary(hitsDto.getHighlight().getSummary()[0]);
                    }
                    if (hitsDto.getHighlight().getName() != null) {
                        hitsDto.get_source().getCategory().setName(hitsDto.getHighlight().getName()[0]);
                    }
                    if (hitsDto.getHighlight().getAuthor() != null) {
                        hitsDto.get_source().setAuthor(hitsDto.getHighlight().getAuthor()[0]);
                    }
                    if (hitsDto.getHighlight().getOperator() != null) {
                        hitsDto.get_source().setOperator(hitsDto.getHighlight().getOperator()[0]);
                    }
                }
            }
        }
        return dataDto;
    }

    /**
     * 描述：插入或更新笔记
     *
     * @param note 笔记
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertOrUpdate(NoteDto note) throws Exception {

        // 1.去除笔记摘要换行
        note.setSummary(note.getSummary().replaceAll("\r\n", ""));
        // 2.获取原笔记
        Note oldNote = !StringUtils.isBlank(note.getId()) ? noteMapper.selectByPrimaryKey(note.getId()) : null;
        // 3.缩略图处理
        String scaleImgUrl = scaleImage(note.getContent(), oldNote);
        if (scaleImgUrl.equals("-1") || StringUtils.isNotBlank(scaleImgUrl)) {
            note.setScaleImgUrl(scaleImgUrl);
        }

        // 4.附件标识设置
        note.setAttachmentFlag(attachmentCheck(note.getContent()));

        // 6.执行保存或更新
        if (StringUtils.isBlank(note.getId())) {
            note.setColumnId("-1");
            note.setCreateTime(new Date());
            //数据库插入
            note.setOperateTime(note.getCreateTime());
            note.setPraiseCount(0);
            note.setColumnOrderNumber(-1);
            noteMapper.insert(note);
            //ES同步插入
            esInsertOrModify(note);
        } else {
            // 修改笔记时设置操作时间，修改专栏时，操作时间不变
            if(StringUtils.isBlank(note.getColumnId())){
                note.setOperateTime(new Date());
            }
            note.setColumnOrderNumber(oldNote.getColumnOrderNumber());
            note.setColumnId(oldNote.getColumnId());
            //数据库更新
            noteMapper.updateByPrimaryKey(note);
            //ES同步更新
            esInsertOrModify(note);
        }

        return note.getId();
    }

    /**
     * 删除笔记
     *
     * @param indexId 笔记id
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String indexId) throws Exception {
        //1.根据id查询笔记
        Note note = noteMapper.selectByPrimaryKey(indexId);
        //2.若存在执行删除
        if (note != null) {
            //删除笔记
            noteMapper.deleteNote(note.getId());
            //3.删除Es数据库笔记
            Delete.Builder builder = new Delete.Builder(indexId);
            Delete delete = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
            JestResult deleteRlt = jestClient.execute(delete);
            //4.若笔记在专栏内，则更改使专栏笔记减一
            if (!"-1".equals(note.getColumnId())) {
                columnsService.updateNoteCount(note.getColumnId());
            }
            //5.若成功设置true，失败则回滚并跑异常
            if (!deleteRlt.isSucceeded()) {
                //回滚
                throw new BusinessException("删除失败");
            }
        }
    }

    /**
     * 描述：ES插入与修改
     *
     * @param note 笔记
     * @throws Exception
     */
    private void esInsertOrModify(NoteDto note) throws Exception {
        // 1.查询类别
        Category category = categorySerive.get(note.getTypeId());
        note.setCategory(category);

        // 2.设置评论数量
        Comment comment = new Comment();
        comment.setNoteId(comment.getNoteId());
        note.setCommentCount(commentService.getCommentCount(note.getId()));

        // 3.ES 插入与修改
        Index.Builder builder = new Index.Builder(note).id(note.getId()).refresh(true);
        Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
        jestClient.execute(index);
    }

    /**
     * 缩略图处理
     *
     * @param content UEditor文本内容
     * @param oldNote 原笔记
     * @return 上传后的缩略图url
     * @throws Exception
     */
    private String scaleImage(String content, Note oldNote) throws Exception {
        // 1.内容为空直接返回
        if (StringUtils.isBlank(content)) {
            return "-1";
        }
        // 2.若原笔记不为空，则是截取笔记原图
        if (oldNote != null) {
            //获取原缩略图
            String scaleImgUrl = oldNote.getScaleImgUrl();
            //若原缩略图为空，则返回空，否则截取原图
            String oldUrl = scaleImgUrl.equals("-1") ? "" : scaleImgUrl.substring(scaleImgUrl.indexOf("&") + 1, scaleImgUrl.length());
            //若笔记原图为不为空，并且新内容中包含原图，则返回原缩略图
            if (!StringUtils.isBlank(oldUrl) && content.contains(oldUrl)) {
                return scaleImgUrl;
            }
        }
        // 3.获取UEditor文本内容中第一张图片的地址
        String url = RegexpUtils.getFirstImgUrlFromUeditor(content, Constant.SCALEIMG_PATH_KEY_WORD);
        // 4.无图片直接返回
        if (StringUtils.isBlank(url)) {
            return "-1";
        }

        // 5.如果图片小于规定大小则返回原图地址
        if (isUploadThumbnail(url)) {
            return StringUtils.assemblyString(url, "&", url);
        }
        return StringUtils.assemblyString(ueditorService.uploadScaleImage(url, 140, 140), "&", url);
    }

    /**
     * 描述：判断是否上传缩略图
     *
     * @param address 图片地址
     * @return true: 不上传 false：上传
     * @throws Exception
     */
    private boolean isUploadThumbnail(String address) throws Exception {
        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        BufferedImage image = ImageIO.read(connection.getInputStream());
        if (image.getWidth() < 140 && image.getHeight() < 140) {
            return true;
        }
        return false;
    }

    /**
     * 是否有附件判断
     *
     * @param content UEditor文本内容
     * @return 0:无附件；1有附件
     * @throws Exception
     */
    private String attachmentCheck(String content) throws Exception {
        // 如果内容不为空 且 包含UEdtor附件图标路径 且 包含公共服务平台附件上传目录，则表示有附件
        if (StringUtils.isNotBlank(content)
                && content.contains(ConfigConstant.fileServerAttaDir)
                && content.contains(Constant.ATTACHMENT_CHECK_STRING)) {
            return ENote.ATTACHMENT_FLAG_IS.getValue();
        }

        return ENote.ATTACHMENT_FLAG_NO.getValue();
    }

    /**
     * 描述：获取展示栏笔记
     *
     * @return 展示栏笔记集合
     * @throws Exception
     */
    @Override
    public Map<String, List<Note>> getShowColumnNote() throws Exception {
        //1.获取最新笔记前五条
        PageHelper.startPage(Constant.DEFAULT_PAGENUM, Constant.PAGESIZE_FIVE);
        Example newExample = new Example(Note.class);
        newExample.createCriteria().andEqualTo("visibleStatus", ENote.VISIBLE_STATUS_PUBLIC.getValue());
        newExample.setOrderByClause("NN_CREATE_TIME desc");
        Page<Note> newPage = (Page<Note>) noteMapper.selectByExample(newExample);
        Map<String, List<Note>> map = new HashMap<>();
        map.put("new", newPage.toPageInfo().getList());
        //2.获取热门笔记
        PageHelper.startPage(Constant.DEFAULT_PAGENUM, Constant.PAGESIZE_FIVE);
        Example hotExample = new Example(Note.class);
        hotExample.createCriteria().andEqualTo("visibleStatus", ENote.VISIBLE_STATUS_PUBLIC.getValue());
        hotExample.setOrderByClause("NN_PRAISE_COUNT desc");
        Page<Note> hotPage = (Page<Note>) noteMapper.selectByExample(hotExample);
        map.put("hot", hotPage.toPageInfo().getList());
        //3.获取推荐笔记
        return map;
    }

    /**
     * 描述：点赞
     *
     * @param indexId 笔记id
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushPraise(String indexId) throws Exception {
        // 根据id查询笔记（数据库）
        Note note = noteMapper.selectByPrimaryKey(indexId);
        NoteDto noteDto = get(indexId);
        if (note != null && noteDto != null) {
            // 数据库点赞数加1
            note.setPraiseCount(note.getPraiseCount() + 1);
            noteMapper.updateByPrimaryKey(note);

            // es 点赞数加1
            noteDto.setPraiseCount(noteDto.getPraiseCount() + 1);
            Index.Builder builder = new Index.Builder(noteDto).id(indexId).refresh(true);
            Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
            jestClient.execute(index);
        }
    }

    /**
     * 获取专栏中的笔记列表
     *
     * @param columnId 专栏id
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 专栏笔记列表
     * @throws Exception
     */
    @Override
    public ESDataDto getColumnsNoteList(String columnId, int pageNum, int pageSize) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().postFilter(termQuery("columnId", columnId)).sort("columnOrderNumber", SortOrder.ASC);
        return query(searchSourceBuilder, pageNum, pageSize);
    }

    /**
     * 获取上一篇下一篇笔记
     *
     * @param noteId 笔记id
     * @return 上一篇下一篇笔记
     * @throws Exception
     */
    @Override
    public Map<String, Note> getPrevAndNextNote(String noteId) throws Exception {
        // 1.获取当前笔记
        Note note = getNote(noteId);

        // 2.查询当前笔记所在栏目下的笔记列表
        Example example = new Example(Note.class);
        example.createCriteria().andEqualTo("columnId", note.getColumnId());
        example.orderBy("columnOrderNumber").asc();
        List<Note> noteList = noteMapper.selectByExample(example);
        int index = 0;
        // 3.找到当前笔记所在的索引值
        for (Note one : noteList) {
            if (one.getId().equals(noteId)) {
                break;
            }
            index++;
        }

        Map<String, Note> map = new HashMap<>();
        // 4.获取上一篇笔记
        map.put("prev", index == 0 ? null : noteList.get(index - 1));

        // 5.获取下一篇笔记
        map.put("next", index == noteList.size() - 1 ? null : noteList.get(index + 1));
        return map;
    }

    /**
     * 描述：根据专栏状态 修改笔记状态
     *
     * @param note 笔记对象
     * @throws Exception
     */
    @Override
    public void modifyStatus(Note note) throws Exception {
        noteMapper.updateByPrimaryKeySelective(note);
    }

    /**
     * 描述：清空笔记专栏Id
     *
     * @param columnId 专栏ID
     * @throws Exception
     */
    @Override
    public void emptyColumnId(String columnId) throws Exception {
        //1.获取专栏笔记列表
        Example example = new Example(Note.class);
        example.createCriteria().andEqualTo("columnId", columnId);
        List<Note> notes = noteMapper.selectByExample(example);

        //2.若不为空，执行清空
        if (!notes.isEmpty()) {
            for (Note note : notes) {
                note.setColumnId("-1");
                //数据库笔记清空
                noteMapper.updateByPrimaryKeySelective(note);

                //ES笔记清空
                NoteDto noteDto = get(note.getId());
                noteDto.setColumnId("-1");
                Index.Builder builder = new Index.Builder(noteDto).id(noteDto.getId()).refresh(true);
                Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_NOTE).build();
                jestClient.execute(index);

            }
        }
    }


    /**
     * 描述：ES分页查询未添加栏目的笔记
     *
     * @param userId     用户ID
     * @param queryParam 查询参数
     * @param noteIds    笔记id集合
     * @param columnId   专栏id
     * @param pageNum    页码
     * @param pageSize   页数
     * @return
     * @throws Exception
     */
    @Override
    public ESDataDto getNotColumnsNoteList(String userId, String queryParam, List<String> noteIds, String columnId, int pageNum, int pageSize) throws Exception {
        SearchSourceBuilder searchSourceBuilder;
        //1.如果查询参数为空则查询所有，否则根据参数查询
        if (StringUtils.isBlank(queryParam)) {
            if (noteIds.isEmpty()) {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(userId, "userId")).should(multiMatchQuery("-1", "columnId")).should(multiMatchQuery(columnId, "columnId"))).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(userId, "userId")).should(multiMatchQuery("-1", "columnId")).should(multiMatchQuery(columnId, "columnId")).mustNot(termsQuery("id", noteIds))).sort("operateTime", SortOrder.DESC);
            }
        } else {
            if (noteIds.isEmpty()) {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.multiMatchQuery(queryParam, "title")).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(userId, "userId")).should(multiMatchQuery("-1", "columnId")).should(multiMatchQuery(columnId, "columnId"))).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.multiMatchQuery(queryParam, "title")).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(userId, "userId")).must(multiMatchQuery(queryParam, "title")).should(multiMatchQuery("-1", "columnId")).should(multiMatchQuery(columnId, "columnId")).mustNot(termsQuery("id", noteIds))).sort("operateTime", SortOrder.DESC);
            }
        }
        return query(searchSourceBuilder, pageNum, pageSize);
    }

    /**
     * 描述：获取精品笔记（前三）
     *
     * @return 精品笔记（前三）
     * @throws Exception
     */
    @Override
    public ESDataDto getQualityNoteList() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.boolQuery().must(multiMatchQuery(ENote.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).mustNot(multiMatchQuery("-1", "scaleImgUrl"))).sort("praiseCount", SortOrder.DESC);
        return query(searchSourceBuilder, 0, 3);
    }
}