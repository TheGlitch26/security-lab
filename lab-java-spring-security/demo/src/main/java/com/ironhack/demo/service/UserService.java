package com.ironhack.demo.service;


import com.ironhack.demo.dto.UserRequest;
import com.ironhack.demo.entity.User;
import com.ironhack.demo.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() ->
                new NullPointerException("User not found!"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NullPointerException("User not found"));
    }

    public User create(UserRequest request){
        User createdUser = new User();
        createdUser.setFullName(request.getFullName());
        createdUser.setEmail(request.getEmail());
        createdUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(createdUser);
    }

    public User update(Long id, UserRequest request){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(request.getEmail() != null && request.getEmail().isBlank()){
            user.setEmail(request.getEmail());
        }
        if(request.getFullName() != null && request.getFullName().isBlank()){
            user.setFullName(request.getFullName());
        }
        if(request.getPassword() != null && request.getPassword().isBlank()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(user);
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
