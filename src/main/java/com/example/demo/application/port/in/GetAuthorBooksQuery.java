package com.example.demo.application.port.in;

import com.example.demo.domain.Book;

import java.util.List;

public interface GetAuthorBooksQuery {
    List<Book> execute(Integer authorId);
}
