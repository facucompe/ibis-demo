package com.example.demo.application.port.out;

import com.example.demo.application.port.in.CreateBook;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;

import java.util.List;

public interface BooksRepository {
    List<Book> getBooks(Author author);
    Book create(CreateBook.CreationParams creationParams, Integer authorId);
}
