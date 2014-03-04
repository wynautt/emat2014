package com.zephyrusapps.edu.emat.service.auth.user;

import com.zephyrusapps.edu.emat.service.auth.AppRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Set;

public class EmatUser implements Serializable {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private EmatRole role;
    private SocialMediaService signInProvider;
    private boolean enabled;

    private EmatUser() {
    }

    public static class Builder {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        private EmatRole role;
        private SocialMediaService signInProvider;
        private boolean enabled;

        public Builder(String email) {
            this.email = email;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(EmatRole role) {
            this.role = role;
            return this;
        }

        public Builder signInProvider(SocialMediaService signInProvider) {
            this.signInProvider = signInProvider;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public EmatUser build() {
            EmatUser user = new EmatUser();

            user.id = id;
            user.email = email;
            user.password = password;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.signInProvider = signInProvider;
            user.enabled = enabled;

            return user;
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public EmatRole getRole() {
        return role;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
