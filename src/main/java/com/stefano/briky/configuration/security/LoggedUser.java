package com.stefano.briky.configuration.security;

import com.stefano.briky.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoggedUser implements UserDetails {

    private final int id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;

    public LoggedUser(Users users) {
        this.id = users.getId();
        this.password = users.getPassword();
        this.email = users.getEmail();
        this.name = users.getName();
        this.surname = users.getSurname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
}
