package com.example.demo.adapter.postgres;

import com.example.demo.adapter.postgres.model.BookVO;
import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.out.BooksRepository;
import com.example.demo.config.exception.NotFoundException;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookPostgresAdapter implements BooksRepository {
    private final BookJPARepository bookJPARepository;
    private final AuthorJPARepository authorJPARepository;

    @Autowired
    public BookPostgresAdapter(BookJPARepository bookJPARepository, AuthorJPARepository authorJPARepository) {
        this.bookJPARepository = bookJPARepository;
        this.authorJPARepository = authorJPARepository;
    }

    @Override
    public List<Book> getBooks(Author author) {
        return bookJPARepository.findAllByAuthorId(author.getId()).stream()
                .map(BookVO::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Book create(CreateBook.CreationParams creationParams, Integer authorId) {
        var author = authorJPARepository.findById(authorId).orElseThrow(NotFoundException::new);

        var book = BookVO.builder()
                .title(creationParams.getTitle())
                .pageCount(creationParams.getPageCount())
                .genre(creationParams.getGenre())
                .author(author)
                .build();

        return bookJPARepository.save(book).toDomain();
    }
}
