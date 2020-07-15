package com.publico.inteview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InteviewApplication {

	public static void main(String[] args) {
		System.out.println("Diretorio executado = " + System.getProperty("user.dir"));
		SpringApplication.run(InteviewApplication.class, args);
	}

}
