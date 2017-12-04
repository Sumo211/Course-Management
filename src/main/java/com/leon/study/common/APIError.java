package com.leon.study.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
class APIError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private APIError() {
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
    }

    APIError(HttpStatus status) {
        this();
        this.status = status;
    }

    APIError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected Error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    APIError(HttpStatus status, String message, Throwable ex) {
        this(status, ex);
        this.message = message;
    }

}
