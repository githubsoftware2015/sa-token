package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan(basePackages = {"com.dongao.**.mapper"})
@SpringBootApplication
public class DongAoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DongAoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DongAoApplication.class, args);
		System.out.println("\n------ Sa-Token-SSO 统一认证中心启动成功 ");
	}

}
