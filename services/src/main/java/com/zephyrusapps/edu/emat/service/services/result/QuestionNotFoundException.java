package com.zephyrusapps.edu.emat.service.services.result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Question not found.")
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
