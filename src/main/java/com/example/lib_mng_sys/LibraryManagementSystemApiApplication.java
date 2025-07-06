package com.example.lib_mng_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryManagementSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApiApplication.class, args);
	}

}
