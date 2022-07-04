package simpleUsers.controllers;

import contract.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import simpleUsers.services.SimpleUserService;

@RestController
public class SimpleUserControllerImpl implements SimpleUserController {

    private SimpleUserService simpleUserService;

    @Override
    public User findByUserName(String userName) {
        return simpleUserService.findByUserName(userName);
    }
}
