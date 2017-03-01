package com.icinfo.lpsp.notebook.note.service;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.note.dto.CategoryDto;
import com.icinfo.lpsp.notebook.note.model.Category;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 描述: 类目测试 .<br>
 *
 * @author YangShangHang
 * @date 2016/11/16
 */
public class CategoryServiceTest extends SpringTxTestCase {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceTest.class);

    @Autowired
    private ICategoryService categoryService;

    /**
     * 描述：测试查询所有类目
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void getCategoryDatas() throws Exception {
        Map<String, List<CategoryDto>> categoryList = categoryService.getCategoryData("dc494c5ab11c11e6a00a00188b839ae8");
        for (Category c : categoryList.get("focus")) {
            logger.info(c.getName());
        }

        for (Category c : categoryList.get("other")) {
            logger.info(c.getName());
        }
    }

    /**
     * 描述：测试查询所有类目
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void getCategoryList() throws Exception {
        List<CategoryDto> list = categoryService.getCategoryList("dc494c5ab11c11e6a00a00188b839ae8");
        for (Category category : list) {
            logger.info(category.getName());
        }
    }


    /**
     * 描述：测试根据id查询类目
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGet() throws Exception {
        Category category = categoryService.get("1a55446cabc911e6a00a00188b839ae8");
        logger.info(category.getName());
    }

    /**
     * 描述：测试类目新增
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testAdd() throws Exception {
        Category category = new Category();
        category.setName("其他");
        category.setOrderNumber(100);
        categoryService.saveOrModify(category);
    }
}
