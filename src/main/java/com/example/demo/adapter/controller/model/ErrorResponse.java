package com.example.demo.adapter.controller.model;

import com.example.demo.adapter.controller.ErrorCode;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    ErrorCode code;
    String message;
}
