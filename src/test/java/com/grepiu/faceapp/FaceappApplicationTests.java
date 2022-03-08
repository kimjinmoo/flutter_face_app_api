package com.grepiu.faceapp;

import com.grepiu.faceapp.extend.Middleware;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FaceappApplicationTests {

	@Autowired
	Middleware middleware;

	@Test
	void contextLoads() {
	}

//	@Test
	void executeEngine() {
		middleware.executeEngine("test","test");
	}
}
