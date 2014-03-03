package com.zephyrusapps.edu.emat.service.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleAccountsAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //UserService userService = UserServiceFactory.getUserService();
        response.sendRedirect("/login");//userService.createLoginURL(request.getRequestURI()));
    }
}