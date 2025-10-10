package ch.ofte.mplayer;

import org.springframework.boot.SpringApplication;

public class TestOftemplayerApplication {

	public static void main(String[] args) {
		SpringApplication.from(MPlayerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
