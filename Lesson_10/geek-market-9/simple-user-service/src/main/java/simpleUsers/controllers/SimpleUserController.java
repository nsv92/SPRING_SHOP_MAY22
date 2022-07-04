package simpleUsers.controllers;

import contract.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SimpleUserController {

    @GetMapping("/simpleUser/findByUserName")
    public User findByUserName(@RequestParam("userName") String userName);
}
