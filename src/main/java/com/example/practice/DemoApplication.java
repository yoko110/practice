package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer implements CommandLineRunner {

 	
    public static void main(String[] args) {
    	SpringApplication.run(DemoApplication.class, args);	
    }
    
    @Autowired
    UserService userService; 

    public void run(String... strings){

	  try {
		  userService.execute();
		System.out.println("処理が成功しました");
	} catch (Exception e) {
		// TODO 自動生成された catch ブロック
		System.out.println("例外発生:" + e.toString());
	}

    }	
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }    
}