package com.zephyrusapps.edu.emat.service.auth;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.HashSet;
import java.util.Set;

public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRegistry userRegistry;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //google or yahoo user (via openid)
        //facebook user (via facebook login)
        User principal = (User) authentication.getPrincipal();

        GaeUser gaeUser = userRegistry.findUser(principal.getUserId());

        if (gaeUser == null) {
            // User not in registry. Needs to register
            Set<AppRole> userRoles = new HashSet<AppRole>();
            //TODO: rewrite to remove the dependency on UserService
            if(UserServiceFactory.getUserService().isUserAdmin()) {
                userRoles.add(AppRole.ADMIN);
            }
            userRoles.add(AppRole.USER);
            String userId = principal.getUserId();
            String userEmail = principal.getEmail();
            String userNickname = principal.getNickname();
            String userName = principal.getNickname();
            boolean userEnabled = true;
            gaeUser = new GaeUser(userId, userEmail, userNickname, userName, userRoles, userEnabled);
            userRegistry.registerUser(gaeUser);
        }

        if (!gaeUser.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        return new GaeUserAuthentication(gaeUser, authentication.getDetails());
    }

    public final boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }

}