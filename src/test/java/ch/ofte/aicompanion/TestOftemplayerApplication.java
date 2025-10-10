package ch.ofte.aicompanion;

import org.springframework.boot.SpringApplication;

public class TestOftemplayerApplication {

	public static void main(String[] args) {
		SpringApplication.from(AICompanionApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
