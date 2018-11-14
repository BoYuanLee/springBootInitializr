/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure.security.config;

import com.boyuanlee.springbootinitializr.configure.security.sms.SmsCodeAuthenticationConfiger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 默认情况role会自动在前面加上ROLE_前缀，所以确保登录的用户角色加上默认前缀
 *
 * @author 李博源
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final SmsCodeAuthenticationConfiger smsCodeAuthenticationConfiger;

	@Autowired
	public SecurityConfig(SmsCodeAuthenticationConfiger smsCodeAuthenticationConfiger) {
		this.smsCodeAuthenticationConfiger = smsCodeAuthenticationConfiger;
	}

	/**
	 * 设置userName&&Password登录时候的密码加密类型
	 * 旧系统使用了MD5加密,现在已不推荐使用
	 * 推荐密码格式：{id}passwordEncoded,例{bcrypt}passwordEncoded
	 * 使用的是spring security5的新api
	 *
	 * @return MD5PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		DelegatingPasswordEncoder passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
		passwordEncoder.setDefaultPasswordEncoderForMatches(new MessageDigestPasswordEncoder("MD5"));
		return passwordEncoder;
	}
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**", "/login", "/index").permitAll()
				.antMatchers("/templates").hasAnyRole("00000003").and()
				.formLogin().loginPage("/login");
	}*/

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userd
	}*/

	/*@Bean
	public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
		DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
		return defaultMethodSecurityExpressionHandler;
	}*/

/*	@Override
	public void configure(WebSecurity web) throws Exception {
		web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
			@Override
			protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
				WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
				root.setDefaultRolePrefix(""); //remove the prefix ROLE_
				return root;
			}
		});
	}*/

	/**
	 * 1.登录页面用户任意访问
	 * 2.任何请求,登录后可以访问
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authenticationProvider().authenticationProvider()
		/*http.authorizeRequests()
				.requestMatchers(EndpointRequest.to("health", "info")).permitAll()
				.requestMatchers(EndpointRequest.toAnyEndpoint().excluding(MappingsEndpoint.class)).hasRole("ACTUATOR")
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/foo").permitAll()
				.antMatchers("/**").hasRole("USER")
				.and()
				.cors()
				.and()
				.httpBasic();*/

		/*http.authorizeRequests()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
				.and()
				.logout().permitAll();*/
		http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().anyRequest().authenticated().anyRequest().hasRole("abc")
				.and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
				.and().logout().permitAll().and().apply(smsCodeAuthenticationConfiger);
		/*http.authorizeRequests().anyRequest().permitAll();*/
		/*http.authorizeRequests().anyRequest().authenticated().anyRequest().hasAnyRole("abc")
				.and().formLogin().loginPage("/login").failureForwardUrl("/login?error").permitAll().and().apply(smsCodeAuthenticationConfiger).and().logout().permitAll();*/
	}

	/**
	 * 防止出现默认的uuid密码
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}*/

		/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.
	}*/
}
