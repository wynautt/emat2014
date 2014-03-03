package com.zephyrusapps.edu.emat.service.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class GaeUserAuthentication implements Authentication {
    private GaeUser user;
    private Object details;
    private boolean isAuthenticated;

    public GaeUserAuthentication(GaeUser gaeUser, Object details) {
        user = gaeUser;
        this.details = details;
        isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if(isAuthenticated == true) {
            throw new IllegalArgumentException();
        }
        this.isAuthenticated = false;
    }

    @Override
    public String getName() {
        return user.getUserId();
    }
}
