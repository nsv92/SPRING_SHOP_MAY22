package orders.controllers;

import contract.entities.DeliveryAddress;
import orders.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryAddressControllerImpl implements DeliveryAddressController{

    @Autowired
    private DeliveryAddressService deliveryAddressService;


    @Override
    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressService.getUserAddresses(userId);
    }
}
