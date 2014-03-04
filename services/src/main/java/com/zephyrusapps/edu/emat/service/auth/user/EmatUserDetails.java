package com.zephyrusapps.edu.emat.service.auth.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EmatUserDetails  extends SocialUser {
    private Long id;
    private String firstName;
    private String lastName;
    //TODO support multiple roles; make Emat role a granted authority
    private EmatRole role;
    private SocialMediaService socialSignInProvider;

    private EmatUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public EmatRole getRole() {
        return role;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String password;
        private EmatRole role;
        private SocialMediaService socialSignInProvider;
        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<GrantedAuthority>();
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
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(EmatRole role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        //TODO review and make it more robust as in http://www.javacodegeeks.com/2013/01/the-builder-pattern-in-practice.html
        public EmatUserDetails build() {
            EmatUserDetails user = new EmatUserDetails(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}

