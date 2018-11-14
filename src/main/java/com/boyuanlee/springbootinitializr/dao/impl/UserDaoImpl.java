/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.dao.impl;

import com.boyuanlee.springbootinitializr.dao.UserDao;
import com.boyuanlee.springbootinitializr.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 李博源
 */
@Service
public class UserDaoImpl implements UserDao {
	@Override
	public User getUserByName(String name) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_abc"));
		return new User(null, null, name, DigestUtils.md5Hex("123456"), authorities);
	}

	@Override
	public User getUserByTelCode(String tel, String code) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_abc"));
		return new User(tel, code, "testUser", code, authorities);
	}
}
