package com.stefano.briky.configuration.security;

import com.stefano.briky.model.Users;
import com.stefano.briky.repository.TokenRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    public TokenAuthorizationFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            Optional<Users> user = tokenRepository.findUserByToken(header.replace(SecurityConstants.TOKEN_PREFIX, ""));

            if (user.isPresent()) {
                LoggedUser loggedUser = new LoggedUser(user.get());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loggedUser, null, null);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response);
    }
}

