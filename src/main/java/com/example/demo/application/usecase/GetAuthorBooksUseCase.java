package com.example.demo.application.usecase;

import com.example.demo.application.port.in.GetAuthorBooksQuery;
import com.example.demo.application.port.out.AuthorRepository;
import com.example.demo.application.port.out.BooksRepository;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAuthorBooksUseCase implements GetAuthorBooksQuery {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public GetAuthorBooksUseCase(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> execute(Integer authorId) {
        var author = authorRepository.getAuthor(authorId);
        return booksRepository.getBooks(author);
    }
}
