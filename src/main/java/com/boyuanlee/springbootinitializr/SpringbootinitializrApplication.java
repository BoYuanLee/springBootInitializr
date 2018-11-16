package com.boyuanlee.springbootinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 李博源
 */
@SpringBootApplication
@EnableCaching
public class SpringbootinitializrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootinitializrApplication.class, args);
	}
}
