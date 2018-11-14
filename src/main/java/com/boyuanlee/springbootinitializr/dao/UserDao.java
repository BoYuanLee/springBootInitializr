/*
 * Copyright (c) 2018. 上海喜泊客信息技术有限公司，包括但不限于EZP Covered、EZP Connected、EZP2Car、EZP2Mobile.
 */

package com.boyuanlee.springbootinitializr.dao;

import com.boyuanlee.springbootinitializr.entity.User;

/**
 * @author 李博源
 */
public interface UserDao {

	User getUserByName(String name);

	User getUserByTelCode(String tel, String code);
}
