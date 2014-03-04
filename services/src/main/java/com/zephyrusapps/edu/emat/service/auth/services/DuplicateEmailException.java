package com.zephyrusapps.edu.emat.service.auth.services;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String s) {
        super(s);
    }
}
