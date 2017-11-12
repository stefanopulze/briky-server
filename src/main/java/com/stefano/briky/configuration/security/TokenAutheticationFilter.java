package com.stefano.briky.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefano.briky.json.LoginJson;
import com.stefano.briky.json.LoginSuccessJson;
import com.stefano.briky.json.UserJson;
import com.stefano.briky.model.Tokens;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAutheticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final TokenFactory tokenFactory;
    private final ModelMapper modelMapper;

    public TokenAutheticationFilter(
            ObjectMapper objectMapper,
            TokenFactory tokenFactory,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager) {
        super();
        setPostOnly(false);
        setUsernameParameter("email");
        setAuthenticationManager(authenticationManager);
        this.objectMapper = objectMapper;
        this.tokenFactory = tokenFactory;
        this.modelMapper = modelMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().contains("application/json")) {
            try {
                LoginJson json = objectMapper.readValue(request.getInputStream(), LoginJson.class);

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        json.getEmail(),
                        json.getPassword());

                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new InsufficientAuthenticationException("Formato dati non valido");
            }
        }

        // Lascio username/password via form
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoggedUser user = (LoggedUser) authResult.getPrincipal();
        Tokens token = tokenFactory.buildUserToken(user);

        LoginSuccessJson json = new LoginSuccessJson();
        json.setToken(token.getValue());
        json.setUser(modelMapper.map(user, UserJson.class));

        response.setStatus(200);
        objectMapper.writeValue(response.getOutputStream(), json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        objectMapper.writeValue(response.getOutputStream(), failed);
    }
}
