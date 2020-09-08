package com.grepiu.faceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class FaceappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceappApplication.class, args);
	}
}
