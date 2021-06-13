package com.example.demo.adapter.postgres.model;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private Integer pageCount;

    @Column
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorVO author;

    public Book toDomain() {
        return Book.builder()
            .id(id)
            .title(title)
            .pageCount(pageCount)
            .genre(genre)
            .build();
    }
}
