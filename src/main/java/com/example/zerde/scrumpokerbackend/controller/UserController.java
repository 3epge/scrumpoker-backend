package com.example.zerde.scrumpokerbackend.controller;

import com.example.zerde.scrumpokerbackend.model.User;
import com.example.zerde.scrumpokerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class UserController {

    private final SimpMessagingTemplate template;

    public UserController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    @PostMapping("users")
    public User newUser(@RequestBody User newUser){
        List<User> users = this.userRepository.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).equals(newUser)) {
                return users.get(i);
            }
        }
        return this.userRepository.save(newUser);
    }

    @PostMapping("users/send/point/{id}")
    public void sendPoint(@PathVariable Long id, @RequestBody String point){
        this.userRepository.findById(id).map(user -> {
            user.setPoint(point);
            this.userRepository.save(user);
            List<User> users = this.userRepository.findAll();
            if(users.size() == 1){
                template.convertAndSend("/notification", "Waiting for others to log in...");
                return null;
            }
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getPoint().isEmpty()) {
                    template.convertAndSend("/notification", "Waiting for everyone to assign the estimates...");
                    return null;
                }
            }
            template.convertAndSend("/notification", users);
            return null;
        });
    }

    @PostMapping("users/remove/points")
    public void removePoints() {
        this.userRepository.findAll().forEach(user -> {
            user.setPoint("");
            this.userRepository.save(user);
        });
    }

    @DeleteMapping("users/remove/{id}")
    public boolean removeUser(@PathVariable Long id) {
        this.userRepository.deleteById(id);
        return true;
    }

}
