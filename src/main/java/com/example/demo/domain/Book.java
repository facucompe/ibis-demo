package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private Integer id;
    private String title;
    private Integer pageCount;
    private Genre genre;
}
