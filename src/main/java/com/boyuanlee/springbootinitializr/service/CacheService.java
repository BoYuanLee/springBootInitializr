/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 李博源
 */
@Service
public class CacheService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final CacheManager cacheManager;

	@Autowired
	public CacheService(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 清空所有缓存
	 */
	public void clearAll() {
		cacheManager.getCacheNames().forEach(this::clear);
	}

	/**
	 * 清空指定缓存
	 *
	 * @param name:缓存名称
	 */
	public void clear(String name) {
		Cache cache = cacheManager.getCache(name);
		if (Objects.nonNull(cache)) {
			cache.clear();
		}
	}

	public void clearKey(String name, String key) {
		Cache cache = cacheManager.getCache(name);
		if (Objects.nonNull(cache)) {
			cache.evict(key);
		}
	}
}
