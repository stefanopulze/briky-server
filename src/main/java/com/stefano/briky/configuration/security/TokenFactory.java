package com.stefano.briky.configuration.security;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.stefano.briky.model.Tokens;
import com.stefano.briky.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenFactory {

    @Autowired
    TokenRepository tokenRepository;

    HashFunction tokenHasher = Hashing.sha256();

    public Tokens buildUserToken(LoggedUser user) {
        Tokens token = tokenRepository.findbyUserId(user.getId());

        if (null == token) {
            String hash = tokenHasher.hashString(
                    "" + user.getId() + new Date(),
                    StandardCharsets.UTF_8
            ).toString();

            token = new Tokens();
            token.setCreatedAt(new Date());
            token.setUserId(user.getId());
            token.setValue(hash);

            tokenRepository.save(token);
        }

        return token;
    }
}
