package com.zephyrusapps.edu.emat.service.services.result;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exam not found")
public class ExamNotFoundException extends RuntimeException {

    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
