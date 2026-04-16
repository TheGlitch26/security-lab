package com.ironhack.demo.controller;

import com.ironhack.demo.dto.UserRequest;
import com.ironhack.demo.entity.User;
import com.ironhack.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/email")
    public User findByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserRequest request){
        userService.findById(id);
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id){
        userService.delete(id);
    }

}
