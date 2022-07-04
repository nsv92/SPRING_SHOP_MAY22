package orders.services;

import contract.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "simple-user-service-client", contextId = "simple-user-service")
public interface SimpleUserService {

    @GetMapping("/simpleUser/findByUserName")
    public User findByUserName(@RequestParam("userName") String userName);
}
