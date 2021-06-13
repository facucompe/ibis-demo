package com.example.demo.adapter.resolver.models;

import com.example.demo.domain.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class BookParams {
    @NotNull
    private String title;
    @NotNull
    private Integer pageCount;
    private Genre genre;
}
