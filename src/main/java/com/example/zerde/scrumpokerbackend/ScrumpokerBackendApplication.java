package com.example.zerde.scrumpokerbackend;

import com.example.zerde.scrumpokerbackend.model.Story;
import com.example.zerde.scrumpokerbackend.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrumpokerBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScrumpokerBackendApplication.class, args);
	}

	@Autowired
	private StoryRepository storyRepository;

	@Override
	public void run(String... args) throws Exception {
		this.storyRepository.save(new Story("implement chat feature", false));
		this.storyRepository.save(new Story("add KYC UI to back office", false));
		this.storyRepository.save(new Story("create FX market stop order", false));
	}

}
