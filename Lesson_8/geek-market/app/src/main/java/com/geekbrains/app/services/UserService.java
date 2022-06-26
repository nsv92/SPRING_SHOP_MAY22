package com.geekbrains.app.services;

import com.geekbrains.app.entites.SystemUser;
import com.geekbrains.app.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService
        extends UserDetailsService
{
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
