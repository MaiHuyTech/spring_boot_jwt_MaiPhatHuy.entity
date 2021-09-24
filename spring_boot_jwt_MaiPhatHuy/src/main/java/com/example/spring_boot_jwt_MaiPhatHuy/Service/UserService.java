package com.example.spring_boot_jwt_MaiPhatHuy.Service;


import com.example.spring_boot_jwt_MaiPhatHuy.Au.UserPrincipal;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.User;

/**
 * @author MPHuy on 24/09/2021
 */
public interface UserService {
        User createUser(User user);
        UserPrincipal findByUsername(String username);
}
