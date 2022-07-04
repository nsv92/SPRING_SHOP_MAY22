package simpleUsers.services;


import contract.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleUsers.repositories.UserRepository;

@Service
public class SimpleUserService {
    private UserRepository userRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public User findByUserName(String username) {
        return userRepository.findOneByUserName(username);
    }

}
