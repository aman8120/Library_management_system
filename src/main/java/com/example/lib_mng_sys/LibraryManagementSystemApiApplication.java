package com.example.lib_mng_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "AuditorAwareImpl")
public class LibraryManagementSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApiApplication.class, args);
	}

}
