package com.example.zerde.scrumpokerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zerde.scrumpokerbackend.model.Story;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
