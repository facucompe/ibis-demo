package com.example.demo.application.port.in;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import lombok.Builder;
import lombok.Value;

public interface CreateBook {
    Book execute(CreationParams creationParams, Integer authorId);

    @Value
    @Builder
    class CreationParams {
        String title;
        Integer pageCount;
        Genre genre;
    }
}
