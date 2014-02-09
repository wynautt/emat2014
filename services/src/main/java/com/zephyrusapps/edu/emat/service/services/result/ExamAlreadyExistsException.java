package com.zephyrusapps.edu.emat.service.services.result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Exam already exists")
public class ExamAlreadyExistsException extends RuntimeException {

    public ExamAlreadyExistsException() {
    }

    public ExamAlreadyExistsException(String message) {
        super(message);
    }

    public ExamAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
