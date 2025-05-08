package com.reallyeasy.cineView;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // activate scheduler(Movie)
public class CineViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineViewApplication.class, args);
	}

}
