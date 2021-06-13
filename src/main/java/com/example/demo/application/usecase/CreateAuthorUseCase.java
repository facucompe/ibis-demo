package com.example.demo.application.usecase;

import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.application.port.out.AuthorRepository;
import com.example.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorUseCase implements CreateAuthor {
    private final AuthorRepository authorRepository;

    @Autowired
    public CreateAuthorUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author execute(CreateAuthor.CreationParams creationParams) {
        return authorRepository.create(creationParams);
    }
}
