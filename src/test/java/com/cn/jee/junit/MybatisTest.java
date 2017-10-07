package com.cn.jee.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.jee.common.mapper.JsonMapper;
import com.cn.jee.modules.log.service.ModLogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class MybatisTest {

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private ModLogService modLogService;

    @Test
    public void collectionParameterTest() {
        String str = "logger";
        logger.debug("hello {} test", str);

        String[] ids = { "1", "2", "3" };
        logger.debug(JsonMapper.toJsonString(modLogService.collectionParameterTest("0", ids)));
    }

}