package com.example.demo.adapter.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.adapter.resolver.models.BookParams;
import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.in.GetAuthorBooksQuery;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Validated
public class BookResolver implements GraphQLMutationResolver, GraphQLQueryResolver {
    private final GetAuthorBooksQuery getAuthorBooksQuery;
    private final CreateBook createBook;

    @Autowired
    public BookResolver(GetAuthorBooksQuery getAuthorBooksQuery, CreateBook createBook) {
        this.getAuthorBooksQuery = getAuthorBooksQuery;
        this.createBook = createBook;
    }

    public List<Book> books(@NotNull Integer authorId) {
        return getAuthorBooksQuery.execute(authorId);
    }

    public Book createBook(@Valid @NotNull BookParams bookParams, @NotNull Integer authorId) {
        var params = CreateBook.CreationParams.builder()
                .title(bookParams.getTitle())
                .pageCount(bookParams.getPageCount())
                .genre(bookParams.getGenre())
                .build();

        return createBook.execute(params, authorId);
    }
}
