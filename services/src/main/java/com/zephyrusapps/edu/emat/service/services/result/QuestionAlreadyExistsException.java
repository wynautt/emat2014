package com.zephyrusapps.edu.emat.service.services.result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Question already exists")
public class QuestionAlreadyExistsException extends RuntimeException {

    public QuestionAlreadyExistsException(String message) {
        super(message);
    }

    public QuestionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
