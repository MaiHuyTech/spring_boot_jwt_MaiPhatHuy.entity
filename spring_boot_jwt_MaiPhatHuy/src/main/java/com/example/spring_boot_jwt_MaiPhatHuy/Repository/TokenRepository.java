package com.example.spring_boot_jwt_MaiPhatHuy.Repository;


import com.example.spring_boot_jwt_MaiPhatHuy.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository
        extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
