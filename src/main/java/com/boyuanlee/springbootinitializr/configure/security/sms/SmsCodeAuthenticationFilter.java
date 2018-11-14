/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author 李博源
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	// ~ Static fields/initializers
	// =====================================================================================

	public static final String SPRING_SECURITY_FORM_TEL_KEY = "tel";
	public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";

	private String telParameter = SPRING_SECURITY_FORM_TEL_KEY;
	private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/login/tel", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if (postOnly && !"POST".equals(request.getMethod())) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String tel = obtainTel(request);
		String code = obtainCode(request);

		tel = Optional.ofNullable(tel).orElse("").trim();
		code = Optional.ofNullable(code).orElse("").trim();

		//创建SmsCodeAuthenticationToken(未认证)
		SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(tel, code);

		//设置用户信息
		setDetails(request, smsCodeAuthenticationToken);

		return this.getAuthenticationManager().authenticate(smsCodeAuthenticationToken);
	}

	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	protected String obtainTel(HttpServletRequest request) {
		return request.getParameter(telParameter);
	}

	protected String obtainCode(HttpServletRequest request) {
		return request.getParameter(codeParameter);
	}
}
