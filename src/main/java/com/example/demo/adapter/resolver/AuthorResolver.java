package com.example.demo.adapter.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.adapter.resolver.models.AuthorParams;
import com.example.demo.adapter.resolver.models.BookParams;
import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.in.GetAuthor;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Validated
public class AuthorResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
    private final GetAuthor getAuthor;
    private final CreateAuthor createAuthor;

    @Autowired
    public AuthorResolver(GetAuthor getAuthor, CreateAuthor createAuthor) {
        this.getAuthor = getAuthor;
        this.createAuthor = createAuthor;
    }

    public Author authorById(Integer authorId) {
        return getAuthor.execute(authorId);
    }

    public Author createAuthor(@Valid @NotNull AuthorParams authorParams) {
        var params = CreateAuthor.CreationParams.builder()
                .name(authorParams.getName())
                .spotlight(authorParams.getSpotlight())
                .build();

        return createAuthor.execute(params);
    }
}
