package br.edu.infnet.rdsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RdsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdsdemoApplication.class, args);
	}

}
