/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author 李博源
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private SmsUserDetailsService smsUserDetailsService;

	public SmsCodeAuthenticationProvider(SmsUserDetailsService smsUserDetailsService) {
		this.smsUserDetailsService = smsUserDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Assert.isInstanceOf(SmsCodeAuthenticationToken.class, authentication, "unsupported authentication type");
		Assert.isTrue(!authentication.isAuthenticated(), "already authenticated");

		// 获得SmsCodeAuthenticationFilter 构建的authenticationToken（未认证）
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		// invoice customize userDetailsService check user info
		SmsUserDetails smsUserDetails = smsUserDetailsService.loadUserByTelCode((String) authenticationToken.getPrincipal(), (String) authentication.getCredentials());

		if (Objects.isNull(smsUserDetails)) {
			throw new InternalAuthenticationServiceException("请输入正确的手机号和验证码");
		}

		//如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
		SmsCodeAuthenticationToken smsCodeAuthenticationTokenResult = new SmsCodeAuthenticationToken(smsUserDetails.getTel(), smsUserDetails.getCode(), smsUserDetails.getAuthorities());
		smsCodeAuthenticationTokenResult.setDetails(authenticationToken.getDetails());
		return smsCodeAuthenticationTokenResult;
	}

	/**
	 * @param authentication
	 * @return
	 * @See ProviderManager
	 * only support SmsCodeAuthenticationToken use this provider
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
