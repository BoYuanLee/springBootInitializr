/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.controller;

import com.boyuanlee.springbootinitializr.configure.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author longman921414@hotmail.com
 * @date 2018/8/14 16:48
 */
@Controller
public class IndexController {

	/**
	 * 登录页面
	 * @return 登录页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 默认主页
	 * @return 默认主页
	 */
	@RequestMapping("/")
	public String index2() {
		return "index";
	}

	@RequestMapping("/testCache")
	public String test() {
		throw new BusinessException(123, "axx");
	}

}
