package com.mycompany.customeraccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The Class CustomerAccountServiceApplication
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CustomerAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountServiceApplication.class, args);
	}

}
