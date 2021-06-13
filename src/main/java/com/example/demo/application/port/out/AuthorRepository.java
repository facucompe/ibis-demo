package com.example.demo.application.port.out;

import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.domain.Author;

public interface AuthorRepository {
    Author getAuthor(Integer authorId);
    Author create(CreateAuthor.CreationParams creationParams);
}
