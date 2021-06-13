package com.example.demo.adapter.controller;

import com.example.demo.application.port.in.CreateAuthor;
import com.example.demo.application.port.in.GetAuthor;
import com.example.demo.config.exception.NotFoundException;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {
    @MockBean
    private GetAuthor getAuthor;

    @MockBean
    private CreateAuthor createAuthor;

    @Autowired
    private MockMvc mvc;

    @Test
    void getAuthorOK() throws Exception {
        var authorId = 1;
        when(getAuthor.execute(authorId)).thenReturn(fakeAuthor());

        mvc.perform(get("/api/v1/authors/{authorId}", authorId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAuthorNOT_FOUND() throws Exception {
        var authorId = 1;
        when(getAuthor.execute(authorId)).thenThrow(new NotFoundException());

        mvc.perform(get("/api/v1/authors/{authorId}", authorId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private Author fakeAuthor() {
        return Author.builder().id(1).name("Orwell").spotlight("Famous author").build();
    }
}
