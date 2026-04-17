package com.ironhack.demo.config;

import com.ironhack.demo.entity.Author;
import com.ironhack.demo.repository.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthorRepository userRepository;

    public CustomUserDetailsService(AuthorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Author not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(author.getEmail())
                .password(author.getPassword())
                .roles(author.getRole().name())
                .build();
    }
}
