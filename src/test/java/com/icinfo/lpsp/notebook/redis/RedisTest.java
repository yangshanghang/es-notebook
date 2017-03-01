/*
 *  Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.lpsp.notebook.redis;

import com.icinfo.framework.core.test.SpringTxTestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

/**
 * 描述:  Redis集群缓存测试.<br>
 *
 * @author xiajunwei
 * @date 2016/3/22
 */
public class RedisTest extends SpringTxTestCase {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Ignore
    public void testClusterSetValue() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        redisTemplate.opsForValue().set("key3", "value3");
        redisTemplate.opsForValue().set("key4", "value4");
    }

    @Test
    @Ignore
    public void testClusterGetValue() {
        Assert.notNull(redisTemplate.opsForValue().get("key1"));
        Assert.notNull(redisTemplate.opsForValue().get("key2"));
        Assert.notNull(redisTemplate.opsForValue().get("key3"));
        Assert.notNull(redisTemplate.opsForValue().get("key4"));

    }
}
