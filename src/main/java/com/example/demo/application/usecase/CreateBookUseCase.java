package com.example.demo.application.usecase;

import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.out.BooksRepository;
import com.example.demo.application.port.out.MessageProducer;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookUseCase implements CreateBook {
    private final BooksRepository booksRepository;
    private final MessageProducer messageProducer;
    @Autowired
    public CreateBookUseCase(BooksRepository booksRepository, MessageProducer messageProducer) {
        this.booksRepository = booksRepository;
        this.messageProducer = messageProducer;
    }

    @Override
    public Book execute(CreationParams creationParams, Integer authorId) {
        var book = booksRepository.create(creationParams, authorId);
        messageProducer.sendBookCreationMessage(book, authorId);
        return book;
    }
}
