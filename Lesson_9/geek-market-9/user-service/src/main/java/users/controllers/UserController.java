package users.controllers;

import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserController {

    @GetMapping("/findByUserName")
    public User findByUserName(String username);

    @PostMapping("/saveUser")
    public boolean save(SystemUser systemUser);

//    @GetMapping("/loadUserByUsername")
//    UserDetails loadUserByUsername(String userName);
}
