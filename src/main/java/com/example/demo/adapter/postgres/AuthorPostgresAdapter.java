package com.example.demo.adapter.postgres;

import com.example.demo.adapter.postgres.model.AuthorVO;
import com.example.demo.adapter.postgres.model.BookVO;
import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.application.port.out.AuthorRepository;
import com.example.demo.config.exception.NotFoundException;
import com.example.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorPostgresAdapter implements AuthorRepository {
    private final AuthorJPARepository authorJPARepository;

    @Autowired
    public AuthorPostgresAdapter(AuthorJPARepository authorJPARepository) {
        this.authorJPARepository = authorJPARepository;
    }

    @Override
    public Author getAuthor(Integer authorId) {
        return authorJPARepository.findById(authorId)
                .map(AuthorVO::toDomain)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Author create(CreateAuthor.CreationParams creationParams) {
        var author = AuthorVO.builder()
                .name(creationParams.getName())
                .spotlight(creationParams.getSpotlight())
                .build();

        return authorJPARepository.save(author).toDomain();
    }
}
