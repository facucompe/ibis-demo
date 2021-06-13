package com.example.demo.application.port.in;

import com.example.demo.domain.Author;
import lombok.Builder;
import lombok.Value;

public interface CreateAuthor {
    Author execute(CreateAuthor.CreationParams creationParams);

    @Value
    @Builder
    class CreationParams {
        String name;
        String spotlight;
    }
}
