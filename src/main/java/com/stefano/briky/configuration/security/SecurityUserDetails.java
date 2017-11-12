package com.stefano.briky.configuration.security;

import com.stefano.briky.model.Users;
import com.stefano.briky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("Email non trovata");
        }

        return new LoggedUser(user.get());
    }
}
