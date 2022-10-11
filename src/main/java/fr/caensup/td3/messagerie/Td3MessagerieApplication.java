package fr.caensup.td3.messagerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Td3MessagerieApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Td3MessagerieApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Td3MessagerieApplication.class);
	}
}
