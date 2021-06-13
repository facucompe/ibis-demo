package com.example.demo.adapter.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthorParams {
    @NotNull
    private String name;
    private String spotlight;
}
