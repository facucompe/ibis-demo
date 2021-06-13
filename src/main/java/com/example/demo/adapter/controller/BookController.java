package com.example.demo.adapter.controller;

import com.example.demo.adapter.controller.model.BookParams;
import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.in.GetAuthorBooksQuery;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class BookController {

    private final GetAuthorBooksQuery getAuthorBooksQuery;
    private final CreateBook createBook;

    @Autowired
    public BookController(GetAuthorBooksQuery getAuthorBooksQuery, CreateBook createBook) {
        this.getAuthorBooksQuery = getAuthorBooksQuery;
        this.createBook = createBook;
    }

    @GetMapping("/authors/{authorId}/books")
    public List<Book> getBooks(@PathVariable(value = "authorId") Integer authorId) {
        return getAuthorBooksQuery.execute(authorId);
    }

    @PostMapping("/authors/{authorId}/books")
    public Book createBook(@PathVariable(value = "authorId") Integer authorId,
                           @Valid @RequestBody BookParams bookParams) {
        var params = CreateBook.CreationParams.builder()
                .title(bookParams.getTitle())
                .pageCount(bookParams.getPageCount())
                .genre(bookParams.getGenre())
                .build();
        return createBook.execute(params, authorId);
    }
}
