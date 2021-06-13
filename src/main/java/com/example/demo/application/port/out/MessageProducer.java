package com.example.demo.application.port.out;

import com.example.demo.domain.Book;

public interface MessageProducer {
    void sendBookCreationMessage(Book book, Integer authorId);
}
