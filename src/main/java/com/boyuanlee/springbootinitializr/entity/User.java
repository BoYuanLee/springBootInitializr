/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.entity;

import com.boyuanlee.springbootinitializr.configure.security.sms.SmsUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author 李博源
 */
public class User extends org.springframework.security.core.userdetails.User implements Serializable, SmsUserDetails {

	private String tel;

	private String code;

	public User(String tel, String code, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.tel = tel;
		this.code = code;
	}

	public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	@Override
	public String getTel() {
		return this.tel;
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
