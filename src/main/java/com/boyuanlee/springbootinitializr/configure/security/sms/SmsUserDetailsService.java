/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.sms;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * similar to {@link UserDetailsService} but loads details by user tel and code, not username
 *
 * @author 李博源
 */
public interface SmsUserDetailsService {

	/**
	 * UserDetailsService#loadUserByUsername(String)
	 *
	 * @param tel  the user tel used to lookup the user details
	 * @param code the user tel's code
	 * @return the SmsUserDetails requested
	 */
	SmsUserDetails loadUserByTelCode(String tel, String code);

}
