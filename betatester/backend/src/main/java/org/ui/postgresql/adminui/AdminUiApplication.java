package org.ui.postgresql.adminui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdminUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminUiApplication.class, args);
	}

}
