package com.example.demo.application.usecase;

import com.example.demo.application.port.in.CreateBook;
import com.example.demo.application.port.out.BooksRepository;
import com.example.demo.application.port.out.MessageProducer;
import com.example.demo.config.exception.NotFoundException;
import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {
    private final BooksRepository booksRepository = mock(BooksRepository.class);
    private final MessageProducer messageProducer = mock(MessageProducer.class);

    private final CreateBookUseCase createBookUseCase = new CreateBookUseCase(booksRepository, messageProducer);

    @Test
    void allDependenciesAreProvided() {
        assertThat(booksRepository).isNotNull();
    }

    @Test
    void bookCreationAuthorNotFound() {
        var params = CreateBook.CreationParams.builder()
                .genre(Genre.DRAMA)
                .pageCount(123)
                .title("1984")
                .build();
        var authorId = 1;

        when(booksRepository.create(params, authorId)).thenThrow(new NotFoundException());

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> createBookUseCase.execute(params, authorId));
    }

    @Test
    void bookCreationOK() {
        var params = CreateBook.CreationParams.builder()
                .genre(Genre.DRAMA)
                .pageCount(123)
                .title("1984")
                .build();
        var authorId = 1;
        var book = Book.builder().genre(Genre.DRAMA).pageCount(123).title("1984").build();

        when(booksRepository.create(params, authorId)).thenReturn(book);

        createBookUseCase.execute(params, authorId);

        verify(messageProducer, times(1)).sendBookCreationMessage(book, authorId);
    }
}
