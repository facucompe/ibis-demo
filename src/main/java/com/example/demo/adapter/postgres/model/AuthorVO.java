package com.example.demo.adapter.postgres.model;

import com.example.demo.domain.Author;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorVO {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    private String spotlight;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "author")
    private List<BookVO> books;

    public Author toDomain() {
        var mappedBooks = books != null? books.stream().map(BookVO::toDomain).collect(Collectors.toList()) : new ArrayList();
        return Author.builder()
                .id(id)
                .name(name)
                .spotlight(spotlight)
                .books(mappedBooks)
                .build();
    }
}
