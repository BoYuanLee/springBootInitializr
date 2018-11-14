/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author longman921414@hotmail.com
 * @date 2018/8/14 16:48
 */
//@Controller
public class LoginController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/")
	public String index2() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
