package ch.raiffeisen.hackzurich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HackzurichApplication {
	public static void main(String[] args) {
		SpringApplication.run(HackzurichApplication.class, args);
	}
}
