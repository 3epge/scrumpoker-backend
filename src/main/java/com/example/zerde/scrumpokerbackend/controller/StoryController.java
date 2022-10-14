package com.example.zerde.scrumpokerbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.zerde.scrumpokerbackend.model.Story;
import com.example.zerde.scrumpokerbackend.repository.StoryRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

    @GetMapping("stories")
    public List<Story> getStories(){
        return this.storyRepository.findAll();
    }

    @PostMapping("stories/activate/{id}")
    public Optional<Story> setActive(@PathVariable Long id){
        return this.storyRepository.findById(id).map(story -> {
            story.setActive(true);
            return this.storyRepository.save(story);
        });
    }

    @PostMapping("stories/deactivate/{id}")
    public Optional<Story> deActivate(@PathVariable Long id){
        return this.storyRepository.findById(id).map(story -> {
            story.setActive(false);
            return this.storyRepository.save(story);
        });
    }
}
