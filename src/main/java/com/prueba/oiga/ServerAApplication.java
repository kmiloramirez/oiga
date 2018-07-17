package com.prueba.oiga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"dominio","persistencia.repositorio","com.prueba.oiga"})
public class ServerAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAApplication.class, args);
	}
}
