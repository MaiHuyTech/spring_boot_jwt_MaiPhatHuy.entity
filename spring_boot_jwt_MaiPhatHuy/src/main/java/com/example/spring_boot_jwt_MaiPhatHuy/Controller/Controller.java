package com.example.spring_boot_jwt_MaiPhatHuy.Controller;

import com.example.spring_boot_jwt_MaiPhatHuy.Service.UserService;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MPHuy on 24/09/2021
 */
@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }
}
