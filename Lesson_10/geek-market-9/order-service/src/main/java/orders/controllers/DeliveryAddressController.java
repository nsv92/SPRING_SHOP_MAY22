package orders.controllers;

import contract.entities.DeliveryAddress;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DeliveryAddressController {

    @GetMapping("/getUserAddresses")
    public List<DeliveryAddress> getUserAddresses(@RequestParam("userId") Long userId);
}
