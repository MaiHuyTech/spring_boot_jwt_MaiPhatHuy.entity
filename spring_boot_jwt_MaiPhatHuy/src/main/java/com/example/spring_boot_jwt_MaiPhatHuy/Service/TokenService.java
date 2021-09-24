package com.example.spring_boot_jwt_MaiPhatHuy.Service;


import com.example.spring_boot_jwt_MaiPhatHuy.entity.Token;

/**
 * @author MPHuy on 24/09/2021
 */
public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}
