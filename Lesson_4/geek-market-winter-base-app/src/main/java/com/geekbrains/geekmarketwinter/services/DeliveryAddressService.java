package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.repositories.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    public void setDeliveryAddressRepository(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepository.findAllByUserId(userId);
    }

    public DeliveryAddress save(User user, String address) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddress(address);
        deliveryAddress.setUser(user);
        deliveryAddressRepository.save(deliveryAddress);
        return deliveryAddress;
    }
    public void save(DeliveryAddress deliveryAddress) {
        deliveryAddressRepository.save(deliveryAddress);
    }
}
