package com.example.spring_boot_jwt_MaiPhatHuy.Service;


import com.example.spring_boot_jwt_MaiPhatHuy.Repository.TokenRepository;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author MPHuy on 24/09/2021
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}

