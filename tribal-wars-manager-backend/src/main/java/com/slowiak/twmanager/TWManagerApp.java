package com.slowiak.twmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TWManagerApp {
	public static void main(String[] args) {
		System.out.println(System.getProperty("name"));
		SpringApplication.run(TWManagerApp.class, args);
	}
}

