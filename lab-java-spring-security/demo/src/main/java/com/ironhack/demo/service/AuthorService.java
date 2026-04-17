package com.ironhack.demo.service;


import com.ironhack.demo.dto.AuthorRequest;
import com.ironhack.demo.entity.Author;
import com.ironhack.demo.repository.AuthorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Author findByEmail(String email){
        return authorRepository.findByEmail(email).orElseThrow(() ->
                new NullPointerException("Author not found!"));
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findById(Long id){
        return authorRepository.findById(id).orElseThrow(() -> new NullPointerException("Author not found"));
    }

    public Author create(AuthorRequest request){
        if(authorRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("An author with this email already exists");
        }
        Author createdAuthor = new Author();
        createdAuthor.setFullName(request.getFullName());
        createdAuthor.setEmail(request.getEmail());
        createdAuthor.setPassword(passwordEncoder.encode(request.getPassword()));
        return authorRepository.save(createdAuthor);
    }

    public Author update(Long id, AuthorRequest request){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        if(request.getEmail() != null && request.getEmail().isBlank()){
            author.setEmail(request.getEmail());
        }
        if(request.getFullName() != null && request.getFullName().isBlank()){
            author.setFullName(request.getFullName());
        }
        if(request.getPassword() != null && request.getPassword().isBlank()){
            author.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return authorRepository.save(author);
    }

    public void delete(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }
}
