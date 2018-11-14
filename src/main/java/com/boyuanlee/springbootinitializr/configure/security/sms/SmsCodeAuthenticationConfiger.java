/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author 李博源
 */
@Component(value = "smsCodeAuthenticationConfiger")
public class SmsCodeAuthenticationConfiger extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


	private final SmsUserDetailsService smsUserDetailsService;
	
	@Autowired
	public SmsCodeAuthenticationConfiger(SmsUserDetailsService smsUserDetailsService) {
		this.smsUserDetailsService = smsUserDetailsService;
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		//自定义SmsCodeAuthenticationFilter过滤器
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));

		//设置自定义SmsCodeAuthenticationProvider的认证器smsUserDetailsService
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider(smsUserDetailsService);

		// 将认证器加入security链，在UsernamePasswordAuthenticationFilter之前执行smsCodeAuthenticationFilter
//		httpSecurity.authenticationProvider(smsCodeAuthenticationProvider).addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
