package com.capo.asignacion_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsignacionRedisApplication {

	public static void main(String[] args) {
		String[] customArgs = new String[] {"points","destinations"};
		SpringApplication.run(AsignacionRedisApplication.class, customArgs);
	}
}
