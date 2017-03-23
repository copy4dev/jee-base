package com.cn.jee.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.jee.modules.log.service.ModLogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
public class UtilsTest {

	public static void main(String[] args) {

	}

	@Autowired
	private ModLogService modLogService;

	@Test
	public void initJunitTest() {
		modLogService.get("1");
		System.out.println("ok");
	}
}
