package com.callecenter.calls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.callcenter"})
@EntityScan("com.callcenter.entities")
@EnableJpaRepositories("com.callcenter.interfaces")
@EnableAutoConfiguration
public class CallsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallsApplication.class, args);
	}
}
