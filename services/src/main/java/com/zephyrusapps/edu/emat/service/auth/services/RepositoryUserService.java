package com.zephyrusapps.edu.emat.service.auth.services;

import com.zephyrusapps.edu.emat.service.auth.registration.RegistrationForm;
import com.zephyrusapps.edu.emat.service.auth.user.EmatRole;
import com.zephyrusapps.edu.emat.service.auth.user.EmatUser;
import com.zephyrusapps.edu.emat.service.auth.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepositoryUserService implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository repository;

    @Autowired
    public RepositoryUserService(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Transactional
    @Override
    public EmatUser registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
        if (emailExist(userAccountData.getEmail())) {
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        String encodedPassword = encodePassword(userAccountData);

        EmatUser.Builder user = new EmatUser.Builder(userAccountData.getEmail())
                .firstName(userAccountData.getFirstName())
                .lastName(userAccountData.getLastName())
                .password(encodedPassword)
                .role(EmatRole.ROLE_USER);

        if (userAccountData.isSocialSignIn()) {
            user
                    .signInProvider(userAccountData.getSignInProvider())
                    .signInProviderKey(userAccountData.getSignInProviderKey());

        }

        EmatUser registered = user.build();

        return repository.save(registered);
    }

    private boolean emailExist(String email) {
        EmatUser user = repository.findByEmail(email);

        if (user != null) {
            return true;
        }

        return false;
    }

    private String encodePassword(RegistrationForm dto) {
        String encodedPassword = null;

        if (dto.isNormalRegistration()) {
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }

        return encodedPassword;
    }
}