package com.example.demo.adapter.kafka;

import com.example.demo.application.port.out.MessageProducer;
import com.example.demo.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements MessageProducer {
    @Override
    public void sendBookCreationMessage(Book book, Integer authorId) { } //TODO
}
