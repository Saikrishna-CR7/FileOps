package com.example.Fileops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.Fileops.service.IFileIOService;


@SpringBootApplication
@ComponentScan("com.example.Fileops")

@Component
public class FileDemo implements CommandLineRunner{

	@Autowired
	IFileIOService fileservice;
	
	public static void main(String[] args) {
		SpringApplication.run(FileDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileservice.readFile();
	}
}
