/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.lpsp.notebook.note.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.lpsp.notebook.common.constant.Constant;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.note.dto.*;
import com.icinfo.lpsp.notebook.note.enums.EColumns;
import com.icinfo.lpsp.notebook.note.enums.ENote;
import com.icinfo.lpsp.notebook.note.mapper.ColumnsMapper;
import com.icinfo.lpsp.notebook.note.model.Category;
import com.icinfo.lpsp.notebook.note.model.Columns;
import com.icinfo.lpsp.notebook.note.model.Note;
import com.icinfo.lpsp.notebook.note.service.ICategoryService;
import com.icinfo.lpsp.notebook.note.service.IColumnsService;
import com.icinfo.lpsp.notebook.note.service.INoteService;
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

import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;


/**
 * 描述: 专栏表 NBOOK_COLUMNS 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年12月28日
 */
@Service
public class ColumnsServiceImpl extends MyBatisServiceSupport implements IColumnsService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(ColumnsServiceImpl.class);

    /**
     * 注入专栏Mapper
     */
    @Autowired
    private ColumnsMapper columnsMapper;

    @Autowired
    private JestClient jestClient;

    /**
     * 注入类别服务
     */
    @Autowired
    private ICategoryService categorySerive;

    @Autowired
    private INoteService noteService;

    /**
     * 描述：ES分页查询专栏
     *
     * @param userId     用户Id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页数
     * @return 专栏列表
     * @throws Exception
     */
    @Override
    public ESColumnsDataDto getList(String userId, String queryParam, int pageNum, int pageSize) throws Exception {

        SearchSourceBuilder searchSourceBuilder;
        // userId为空表示未登录
        if (StringUtils.isBlank(userId)) {
            //1.判断参数是否为空
            if (StringUtils.isBlank(queryParam)) {
                //为空查询所有
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery())
                        .postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(EColumns.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).mustNot(multiMatchQuery(0, "noteCount")))
                        .sort("operateTime", SortOrder.DESC);
            } else {
                //不为空查询指定参数
                QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(multiMatchQuery(queryParam, "name").boost(1000))
                        .should(multiMatchQuery(queryParam, "introduce").boost(100))
                        .should(multiMatchQuery(queryParam, "categoryName").boost(2))
                        .should(multiMatchQuery(queryParam, "author"));
                searchSourceBuilder = new SearchSourceBuilder().query(queryBuilder).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(EColumns.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).mustNot(multiMatchQuery(0, "noteCount")));
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
                QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                        .should(multiMatchQuery(queryParam, "name").boost(1000))
                        .should(multiMatchQuery(queryParam, "introduce").boost(100))
                        .should(multiMatchQuery(queryParam, "categoryName").boost(2))
                        .should(multiMatchQuery(queryParam, "author"));
                searchSourceBuilder = new SearchSourceBuilder()
                        .postFilter(multiMatchQuery(userId, "userId")).query(queryBuilder);
            }
        }
        //2.查询
        return query(searchSourceBuilder, pageNum, pageSize);

    }

    /**
     * 描述：ES根据类目分页查询专栏
     *
     * @param userId     用户id
     * @param categoryId 类目id
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 专栏列表
     * @throws Exception
     */
    @Override
    public ESColumnsDataDto getListByCategory(String userId, String categoryId, String queryParam, int pageNum, int pageSize) throws Exception {
        SearchSourceBuilder searchSourceBuilder;
        // 用户未登录
        if (StringUtils.isBlank(userId)) {
            if (StringUtils.isBlank(queryParam)) {
                searchSourceBuilder = new SearchSourceBuilder().query(multiMatchQuery(categoryId, "categoryId")).postFilter(QueryBuilders.boolQuery().must(multiMatchQuery(EColumns.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).mustNot(multiMatchQuery(0, "noteCount"))).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.boolQuery()
                        .should(multiMatchQuery(queryParam, "name").boost(1000))
                        .should(multiMatchQuery(queryParam, "introduce").boost(100))
                        .should(multiMatchQuery(queryParam, "categoryName").boost(2))
                        .should(multiMatchQuery(queryParam, "author")))
                        .postFilter(QueryBuilders.boolQuery().
                                must(multiMatchQuery(EColumns.VISIBLE_STATUS_PUBLIC.getValue(), "visibleStatus")).
                                must(multiMatchQuery(categoryId, "categoryId"))
                                .mustNot(multiMatchQuery(0, "noteCount")));
            }
        }
        // 用户已登录
        else {
            if (StringUtils.isBlank(queryParam)) {
                searchSourceBuilder = new SearchSourceBuilder().query(multiMatchQuery(categoryId, "categoryId")).postFilter(multiMatchQuery(userId, "userId")).sort("operateTime", SortOrder.DESC);
            } else {
                searchSourceBuilder = new SearchSourceBuilder()
                        .postFilter(QueryBuilders.boolQuery().
                                must(multiMatchQuery(categoryId, "categoryId")).
                                must(multiMatchQuery(userId, "userId")))
                        .query(QueryBuilders.boolQuery()
                                .should(multiMatchQuery(queryParam, "name").boost(1000))
                                .should(multiMatchQuery(queryParam, "introduce").boost(100))
                                .should(multiMatchQuery(queryParam, "categoryName").boost(2))
                                .should(multiMatchQuery(queryParam, "author")));
            }
        }
        return query(searchSourceBuilder, pageNum, pageSize);
    }


    /**
     * 描述：执行ES查询
     *
     * @param searchSourceBuilder es查询语句
     * @param pageNum             页码
     * @param pageSize            页大小
     * @return 查询结果
     * @throws Exception
     */
    private ESColumnsDataDto query(SearchSourceBuilder searchSourceBuilder, int pageNum, int pageSize) throws Exception {
        // 2.创建查询对象
        Search search = new Search.Builder(searchSourceBuilder.highlight(new HighlightBuilder()
                .field("name").field("introduce").field("categoryName").field("author")
                .preTags("<B class = 'es-highlight'>")
                .postTags("</B>")).toString())
                .addIndex(Constant.INDEX_NAME_NOTE)
                .addType("columns")
                .setParameter("from", pageNum * pageSize)
                .setParameter("size", pageSize)
                .build();
        // 3.执行查询返回结果
        JestResult result = jestClient.execute(search);
        ESColumnsDataDto esColumnsDataDto = new ESColumnsDataDto();
        // 4.若查询结果是成功将结果转换对象
        if (result.isSucceeded()) {
            String str = result.getJsonObject().get("hits").toString();
            ObjectMapper object = new ObjectMapper();
            esColumnsDataDto = object.readValue(str, ESColumnsDataDto.class);
            esColumnsDataDto.setTook(result.getJsonObject().get("took").getAsLong());
            for (ColumnsHitsDto hitsDto : esColumnsDataDto.getHits()) {

                //设置高亮文本
                if (hitsDto.getHighlight() != null) {
                    if (hitsDto.getHighlight().getName() != null) {
                        hitsDto.get_source().setName(hitsDto.getHighlight().getName()[0]);
                    }
                    if (hitsDto.getHighlight().getIntroduce() != null) {
                        hitsDto.get_source().setIntroduce(hitsDto.getHighlight().getIntroduce()[0]);
                    }
                    if (hitsDto.getHighlight().getCategoryName() != null) {
                        hitsDto.get_source().setCategoryName(hitsDto.getHighlight().getCategoryName()[0]);
                    }
                    if (hitsDto.getHighlight().getAuthor() != null) {
                        hitsDto.get_source().setAuthor(hitsDto.getHighlight().getAuthor()[0]);
                    }
                }
            }

        }
        return esColumnsDataDto;
    }

    /**
     * 描述：新增修改专栏
     *
     * @param list       专栏笔记列表
     * @param columnsDto 专栏DTO对象
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdate(List<Note> list, ColumnsDto columnsDto) throws Exception {
        // 1.去除专栏介绍换行
        columnsDto.setIntroduce(columnsDto.getIntroduce().replaceAll("\r\n", ""));
        // 2.如果专栏id为空 则新增，否则修改
        if (StringUtils.isBlank(columnsDto.getId())) {
            //新增 数据库插入
            columnsDto.setCreateTime(new Date());
            columnsDto.setOperateTime(columnsDto.getCreateTime());
            columnsMapper.insert(columnsDto);
            //ES 插入
            esInsertOrModify(list, columnsDto);
        } else {
            //清空 专栏笔记中的专栏ID
            noteService.emptyColumnId(columnsDto.getId());
            //数据库更新 专栏
            columnsDto.setOperateTime(new Date());
            columnsMapper.updateByPrimaryKeySelective(columnsDto);
            //设置创建时间
            columnsDto.setCreateTime(get(columnsDto.getId()).getCreateTime());
            //ES 更新
            esInsertOrModify(list, columnsDto);
        }
        return true;
    }

    /**
     * 描述：ES插入与修改
     *
     * @param list       专栏笔记列表
     * @param columnsDto 专栏DTO对象
     * @throws Exception
     */
    private void esInsertOrModify(List<Note> list, ColumnsDto columnsDto) throws Exception {

        //1.获取类别
        Category category = categorySerive.get(columnsDto.getCategoryId());
        //2.DTO设置类别名称
        columnsDto.setCategoryName(category.getName());
        //3.设置noteList属性为空
        columnsDto.setNoteList(null);
        //4.设置笔记数量
        columnsDto.setNoteCount(list.size());
        //4.ES 插入与修改
        Index.Builder builder = new Index.Builder(columnsDto).id(columnsDto.getId()).refresh(true);
        Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_COLUMNS).build();
        jestClient.execute(index);

        //5.修改专栏笔记列表状态
        modifyNoteStatus(list, columnsDto, category);
    }

    /**
     * 描述：修改笔记状态
     *
     * @param list       专栏笔记列表
     * @param columnsDto 专栏DTO对象
     * @param category   类别对象
     * @throws Exception
     */
    private void modifyNoteStatus(List<Note> list, ColumnsDto columnsDto, Category category) throws Exception {
        String id = columnsDto.getId();
        String categoryId = columnsDto.getCategoryId();
        String visibleStatus = columnsDto.getVisibleStatus();
        for (Note note : list) {
            note.setTypeId(categoryId);
            note.setColumnId(id);
            if (ENote.VISIBLE_STATUS_PUBLIC.getValue().equals(visibleStatus)) {
                note.setVisibleStatus(visibleStatus);
            }
            //1.数据库 修改
            noteService.modifyStatus(note);
            //2.ES 修改
            //根据id ES获取笔记dto
            NoteDto noteDto = noteService.get(note.getId());
            if (ENote.VISIBLE_STATUS_PUBLIC.getValue().equals(visibleStatus)) {
                noteDto.setVisibleStatus(visibleStatus);
            }
            noteDto.setTypeId(categoryId);
            noteDto.setCategory(category);
            noteDto.setColumnId(id);
            noteDto.setColumnOrderNumber(note.getColumnOrderNumber());
            //修改ES 笔记
            noteService.insertOrUpdate(noteDto);
        }
    }


    /**
     * 描述:删除专栏
     *
     * @param columnId 专栏Id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String columnId) throws Exception {
        //1.根据id查询专栏
        Columns columns = columnsMapper.selectByPrimaryKey(columnId);
        //2.若存在执行删除
        if (columns != null) {
            //删除专栏
            columnsMapper.deleteByPrimaryKey(columnId);
            //3.删除 ES 专栏
            Delete.Builder builder = new Delete.Builder(columnId);
            Delete delete = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_COLUMNS).build();
            JestResult deleteRlt = jestClient.execute(delete);
            //4.若成功设置true，失败则回滚并跑异常
            if (!deleteRlt.isSucceeded()) {
                //回滚
                throw new BusinessException("删除失败");
            }
            //5.清空 专栏笔记中的专栏ID
            noteService.emptyColumnId(columnId);
        }
    }

    /**
     * 描述：ES根据索引id 专栏
     *
     * @param indexId 索引ID
     * @return 笔记详情
     * @throws Exception
     */
    private ColumnsDto get(String indexId) throws Exception {
        // 1.创建Get对象
        Get get = new Get.Builder(Constant.INDEX_NAME_NOTE, indexId).type(Constant.INDEX_TYPE_COLUMNS).build();
        JestResult result = jestClient.execute(get);
        // 2.如果查询失败，返回null
        if (!result.isSucceeded()) {
            return null;
        }
        // 3.查询结果转换成对象
        ColumnsDto columnsDto = result.getSourceAsObject(ColumnsDto.class);
        return columnsDto == null ? null : columnsDto;
    }

    /**
     * 获取栏目详情
     *
     * @param columnId 栏目id
     * @return 栏目详情
     * @throws Exception
     */
    @Override
    public Columns getColumn(String columnId) throws Exception {
        return columnsMapper.selectByPrimaryKey(columnId);
    }

    /**
     * 描述：根据栏目id 修改笔记数量
     *
     * @param columnId 栏目id
     * @throws Exception
     */
    @Override
    public void updateNoteCount(String columnId) throws Exception {
        //1.根据栏目id获取栏目信息
        ColumnsDto columnsDto = get(columnId);
        columnsDto.setNoteCount(columnsDto.getNoteCount() - 1);
        //2.ES修改
        Index.Builder builder = new Index.Builder(columnsDto).id(columnsDto.getId()).refresh(true);
        Index index = builder.index(Constant.INDEX_NAME_NOTE).type(Constant.INDEX_TYPE_COLUMNS).build();
        jestClient.execute(index);
    }
}