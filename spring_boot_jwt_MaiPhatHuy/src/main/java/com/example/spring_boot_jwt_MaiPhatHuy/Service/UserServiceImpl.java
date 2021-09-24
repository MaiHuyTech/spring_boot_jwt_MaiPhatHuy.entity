package com.example.spring_boot_jwt_MaiPhatHuy.Service;

import com.example.spring_boot_jwt_MaiPhatHuy.Au.UserPrincipal;
import com.example.spring_boot_jwt_MaiPhatHuy.Repository.UserRepository;
import com.example.spring_boot_jwt_MaiPhatHuy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
/**
 * @author MPHuy on 24/09/2021
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }
    @Override
    public UserPrincipal findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();

            if (null != user.getRoles())

                user.getRoles().forEach(r -> {
                authorities.add(r.getRoleKey());
                r.getPermissions().forEach(
                        p -> authorities.add(p.getPermissionKey()));
            });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;

    }

}