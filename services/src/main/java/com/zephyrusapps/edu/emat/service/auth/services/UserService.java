package com.zephyrusapps.edu.emat.service.auth.services;

import com.zephyrusapps.edu.emat.service.auth.registration.RegistrationForm;
import com.zephyrusapps.edu.emat.service.auth.user.EmatUser;

public interface UserService {
    public EmatUser registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}