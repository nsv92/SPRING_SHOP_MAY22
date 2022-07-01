package market.services;

import contract.entities.SystemUser;
import contract.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service-client", contextId = "user-service")
public interface UserService {

    @GetMapping("/getUser/{username}")
    User findByUserName(@PathVariable("username") String username);

    @PostMapping("/saveUser")
    boolean save(@RequestBody SystemUser systemUser);

//    @GetMapping("/loadUserByUsername/{username}")
//    UserDetails loadUserByUsername(@PathVariable("username") String userName);
}


