/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.configure;

/**
 * 业务异常
 * @author longman921414@hotmail.com
 * @date 2018/11/15 15:27
 */
public class BusinessException extends RuntimeException {

	private int code;

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
