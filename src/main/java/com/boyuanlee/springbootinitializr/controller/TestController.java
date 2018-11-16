/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.controller;

import com.boyuanlee.springbootinitializr.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longman921414@hotmail.com
 * @date 2018/11/15 18:33
 */
@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("cachePut")
	public String get(String id) {
		return testService.get(id);
	}

	@GetMapping("cacheEvict")
	public String evict(String id) {
		return testService.evict(id);
	}
}
