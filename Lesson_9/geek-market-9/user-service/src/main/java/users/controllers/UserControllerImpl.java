package users.controllers;

import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import users.services.UserService;

@RestController
public class UserControllerImpl implements UserController{

    @Autowired
    private UserService userService;

    @Override
    public User findByUserName(String username) {
        return userService.findByUserName(username);
    }

    @Override
    public boolean save(SystemUser systemUser) {
        return userService.save(systemUser);
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) {
//        return userService.loadUserByUsername(userName);
//    }
}
