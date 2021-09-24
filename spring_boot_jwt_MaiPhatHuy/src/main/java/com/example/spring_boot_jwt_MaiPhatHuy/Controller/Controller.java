package com.example.spring_boot_jwt_MaiPhatHuy.Controller;

import com.example.spring_boot_jwt_MaiPhatHuy.Au.UserPrincipal;
import com.example.spring_boot_jwt_MaiPhatHuy.Service.TokenService;
import com.example.spring_boot_jwt_MaiPhatHuy.Service.UserService;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.Token;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.User;
import com.example.spring_boot_jwt_MaiPhatHuy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MPHuy on 24/09/2021
 */
@RestController
public class Controller {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello iuh Iam Student");
    }
}
