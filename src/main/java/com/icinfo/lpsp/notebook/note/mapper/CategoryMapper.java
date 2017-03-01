/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年11月15日
 * @version 2.0
 */
package com.icinfo.lpsp.notebook.note.mapper;

import com.icinfo.framework.mybatis.mapper.common.Mapper;
import com.icinfo.lpsp.notebook.note.dto.CategoryDto;
import com.icinfo.lpsp.notebook.note.model.Category;

import java.util.List;


/**
 * 描述:  NBOOK_CATEGORY 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年11月15日
 */
public interface CategoryMapper extends Mapper<Category> {

    /**
     * 描述：根据用户id获取用户关注类别
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<CategoryDto> selectList(String id) throws Exception;

    /**
     * 描述：获取全部类别
     *
     * @return
     * @throws Exception
     */
    List<CategoryDto> selectAllList() throws Exception;
}