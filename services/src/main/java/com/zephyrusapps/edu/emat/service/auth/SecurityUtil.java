package com.zephyrusapps.edu.emat.service.auth;

import com.zephyrusapps.edu.emat.service.auth.user.EmatUser;
import com.zephyrusapps.edu.emat.service.auth.user.EmatUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static void logInUser(EmatUser user) {
        EmatUserDetails userDetails = EmatUserDetails.builder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}