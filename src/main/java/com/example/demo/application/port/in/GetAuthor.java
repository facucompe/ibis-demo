package com.example.demo.application.port.in;

import com.example.demo.domain.Author;

public interface GetAuthor {
    Author execute(Integer id);
}
