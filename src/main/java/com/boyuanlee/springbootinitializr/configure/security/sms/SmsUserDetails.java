/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.sms;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author 李博源
 */
public interface SmsUserDetails extends UserDetails {

	/**
	 * Returns the tel used to authenticate the user.
	 *
	 * @return the tel
	 */
	String getTel();

	/**
	 * Returns the code used to authenticate the user. Cannot return <code>null</code>.
	 *
	 * @return the code (never <code>null</code>)
	 */
	String getCode();


}
