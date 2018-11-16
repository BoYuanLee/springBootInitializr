/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * @author longman921414@hotmail.com
 * @date 2018/11/13 17:56
 */
@Configuration
@PropertySource("classpath:cache.properties")
public class CacheConfig {

	private static String cachePermanentName;

	private static long cachePermanentExpireAfterWrite;

	private static int cachePermanentInitialCapacity;

	private static String cacheImpermanentName;

	private static long cacheImpermanentExpireAfterWrite;

	private static int cacheImpermanentInitialCapacity;

	private Logger logger = Logger.getLogger(CacheConfig.class.getName());

	/**
	 * 持久缓存，写入后24小时更新一次
	 *
	 * @return CaffeineCache
	 */
	@Bean
	public CaffeineCache cachePermanent() {
		logger.info("cachePermanent *****");
		return buildCache(cachePermanentName, cachePermanentInitialCapacity, Duration.ofHours(cachePermanentExpireAfterWrite));
	}

	/**
	 * 非持久缓存，短时间保存，会在某个时间点之后过期
	 */
	@Bean
	public CaffeineCache cacheImpermanent() {
		logger.info("cacheImpermanent *****");
		return buildCache(cacheImpermanentName, cacheImpermanentInitialCapacity, Duration.ofMinutes(cacheImpermanentExpireAfterWrite));
	}

	/**
	 * 新建缓存
	 *
	 * @param name：缓存名称
	 * @param initialCapacity：初始容量，            -1不限制
	 * @param duration：缓存生命周期：expireAfterWrite
	 * @return 缓存
	 */
	private static CaffeineCache buildCache(String name, int initialCapacity, Duration duration) {
		return new CaffeineCache(name, Caffeine.newBuilder().initialCapacity(initialCapacity).expireAfterWrite(duration).build());
	}


	@Value("${cache.permanent.name}")
	public void setCachePermanentName(String cachePermanentName) {
		CacheConfig.cachePermanentName = cachePermanentName;
	}

	@Value("${cache.permanent.expireAfterWrite}")
	public void setCachePermanentExpireAfterWrite(long cachePermanentExpireAfterWrite) {
		CacheConfig.cachePermanentExpireAfterWrite = cachePermanentExpireAfterWrite;
	}

	@Value("${cache.permanent.initialCapacity}")
	public void setCachePermanentInitialCapacity(int cachePermanentInitialCapacity) {
		CacheConfig.cachePermanentInitialCapacity = cachePermanentInitialCapacity;
	}

	@Value("${cache.impermanent.name}")
	public void setCacheImpermanentName(String cacheImpermanentName) {
		CacheConfig.cacheImpermanentName = cacheImpermanentName;
	}

	@Value("${cache.impermanent.expireAfterWrite}")
	public void setCacheImpermanentExpireAfterWrite(long cacheImpermanentExpireAfterWrite) {
		CacheConfig.cacheImpermanentExpireAfterWrite = cacheImpermanentExpireAfterWrite;
	}

	@Value("${cache.impermanent.initialCapacity}")
	public void setCacheImpermanentInitialCapacity(int cacheImpermanentInitialCapacity) {
		CacheConfig.cacheImpermanentInitialCapacity = cacheImpermanentInitialCapacity;
	}

	public static String getCachePermanentName() {
		return cachePermanentName;
	}

	public static String getCacheImpermanentName() {
		return cacheImpermanentName;
	}
}
