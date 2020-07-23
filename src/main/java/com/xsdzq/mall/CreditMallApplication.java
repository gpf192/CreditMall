package com.xsdzq.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CreditMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditMallApplication.class, args);
	}

}
