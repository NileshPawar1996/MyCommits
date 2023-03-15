package com.sunbeam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaanamvcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GaanamvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DbInit component added few records for testing!");
		System.out.println("Refer test cases for usage!");
	}
	
}


