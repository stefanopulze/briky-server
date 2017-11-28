package com.stefano.briky.utils;

import com.stefano.briky.configuration.security.LoggedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public static final LoggedUser getUser() {
        return (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
