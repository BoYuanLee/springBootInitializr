package com.boyuanlee.springbootinitializr;

import com.boyuanlee.springbootinitializr.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootinitializrApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private CacheService cacheService;

	@Test
	public void test() {
		System.out.println("cccc");
		cacheService.clearAll();
	}

}
