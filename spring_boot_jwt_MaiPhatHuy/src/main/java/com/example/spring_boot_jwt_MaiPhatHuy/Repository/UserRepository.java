package com.example.spring_boot_jwt_MaiPhatHuy.Repository;


import com.example.spring_boot_jwt_MaiPhatHuy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
