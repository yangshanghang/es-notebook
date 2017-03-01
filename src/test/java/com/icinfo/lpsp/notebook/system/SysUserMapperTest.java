/*
 *  Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.lpsp.notebook.system;

import com.icinfo.framework.core.test.SpringTxTestCase;
import com.icinfo.lpsp.notebook.note.service.CategoryServiceTest;
import com.icinfo.lpsp.notebook.system.mapper.SysUserMapper;
import com.icinfo.lpsp.notebook.system.model.SysUser;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:  .<br>
 *
 * @author xiajunwei
 * @date 2016年04月11日
 */
public class SysUserMapperTest extends SpringTxTestCase {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(SysUserMapperTest.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    @Ignore
    public void testSelect() {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey("a1");
        logger.info(sysUser.getLoginName());
    }
}
