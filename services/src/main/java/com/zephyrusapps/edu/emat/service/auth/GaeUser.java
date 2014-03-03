package com.zephyrusapps.edu.emat.service.auth;

import java.io.Serializable;
import java.util.Set;

public class GaeUser implements Serializable {
    private final String userId;
    private final String email;
    private final String nickname;
    private final String name;
    private final Set<AppRole> authorities;
    private final boolean enabled;


    public GaeUser(String userId, String email, String nickname, String name, Set<AppRole> authorities, boolean enabled) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public Set<AppRole> getAuthorities() {
        return authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }
}