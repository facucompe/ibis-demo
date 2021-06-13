package com.example.demo.application.usecase;

import com.example.demo.application.port.in.GetAuthor;
import com.example.demo.application.port.out.AuthorRepository;
import com.example.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAuthorUseCase implements GetAuthor {
    private final AuthorRepository authorRepository;

    @Autowired
    public GetAuthorUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author execute(Integer id) {
        return authorRepository.getAuthor(id);
    }
}
