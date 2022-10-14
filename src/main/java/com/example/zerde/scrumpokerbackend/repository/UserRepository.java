package com.example.zerde.scrumpokerbackend.repository;

import com.example.zerde.scrumpokerbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
