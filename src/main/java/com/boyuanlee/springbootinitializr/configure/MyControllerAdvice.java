/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebBindingInitializer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longman921414@hotmail.com
 * @date 2018/11/15 14:41
 */
@ControllerAdvice
public class MyControllerAdvice implements WebBindingInitializer {


	/*@ExceptionHandler(YourException.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
	}*/

	/**
	 * 自定义异常处理
	 *
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public String exceptionHandler(HttpServletRequest request, Exception ex) {
		// todo
		if (ex instanceof BusinessException) {
			return "业务异常";
		}
		return "非业务异常";
	}

	/**
	 * 注册数据绑定处理器
	 *
	 * @param binder
	 */
	@Override
	public void initBinder(WebDataBinder binder) {

	}
}
