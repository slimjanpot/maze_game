package nl.fontysS3_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.fontysS3_project.persistence")
public class MazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MazeApplication.class, args);
	}

}
