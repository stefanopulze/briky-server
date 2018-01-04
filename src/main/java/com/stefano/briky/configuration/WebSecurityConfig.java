package com.stefano.briky.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefano.briky.configuration.security.TokenAutheticationFilter;
import com.stefano.briky.configuration.security.TokenAuthorizationFilter;
import com.stefano.briky.configuration.security.TokenFactory;
import com.stefano.briky.repository.TokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TokenFactory tokenFactory;

    @Autowired
    ModelMapper modelMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .securityContext().disable()
                .headers().disable()
                .rememberMe().disable()
                .requestCache().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(new TokenAuthorizationFilter(tokenRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new TokenAutheticationFilter(objectMapper, tokenFactory, modelMapper, authenticationManager()))
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }

        };
    }


}
