package com.example.demo.adapter.controller;

import com.example.demo.adapter.controller.model.AuthorParams;
import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.application.port.in.GetAuthor;
import com.example.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class AuthorController {
    private final GetAuthor getAuthor;
    private final CreateAuthor createAuthor;

    @Autowired
    public AuthorController(GetAuthor getAuthor, CreateAuthor createAuthor) {
        this.getAuthor = getAuthor;
        this.createAuthor = createAuthor;
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthor(@PathVariable(value = "authorId") Integer authorId) {
        return getAuthor.execute(authorId);
    }

    @PostMapping("/authors")
    public Author createAuthor(@Valid @RequestBody AuthorParams authorParams) {
        var params = CreateAuthor.CreationParams.builder()
                .name(authorParams.getName())
                .spotlight(authorParams.getSpotlight())
                .build();
        return createAuthor.execute(params);
    }
}
