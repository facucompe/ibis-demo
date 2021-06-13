package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Author {
    private Integer id;
    private String name;
    private String spotlight;
    private List<Book> books;
}
