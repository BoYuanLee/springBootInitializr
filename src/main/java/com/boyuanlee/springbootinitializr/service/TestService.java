/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @author longman921414@hotmail.com
 * @date 2018/11/15 16:47
 */
@Service
public class TestService {
	private static Logger logger = Logger.getLogger(TestService.class.getName());

	@Cacheable(value = "cachePermanent")
	public String get(String id) {
		logger.info("id#######{}" + id);
		return id;
	}

	@CacheEvict(value = "cachePermanent")
	public String evict(String id) {
		logger.info("id#######{}" + id);
		return id;
	}


}
