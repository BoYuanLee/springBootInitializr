/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.config;

import com.boyuanlee.springbootinitializr.configure.security.sms.SmsUserDetails;
import com.boyuanlee.springbootinitializr.configure.security.sms.SmsUserDetailsService;
import com.boyuanlee.springbootinitializr.dao.UserDao;
import com.boyuanlee.springbootinitializr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 李博源
 */
@Component
public class UserDetailsServiceImpl implements SmsUserDetailsService, UserDetailsService {

	private final UserDao userDao;

	@Autowired
	public UserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public SmsUserDetails loadUserByTelCode(String tel, String code) {
		User user = userDao.getUserByTelCode(tel, code);
		if ("123".equals(code)) {
			user = null;
		}
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		return new User(user.getTel(), user.getCode(), user.getUsername(), user.getCode(), user.getAuthorities());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByName(username);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}

	/*

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByName(username);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("abc"));
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return DigestUtils.md5Hex((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return Objects.equals(DigestUtils.md5Hex((String) rawPassword), encodedPassword);
			}
		};
	}*/
}
