package com.zephyrusapps.edu.emat.service.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmatUser user = repository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        EmatUserDetails principal = EmatUserDetails.builder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();

        return principal;
    }
}