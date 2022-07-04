package orders.services;

import contract.entities.DeliveryAddress;
import orders.repositories.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepository.findAllByUserId(userId);
//		ArrayList<DeliveryAddress> listOfAddresses = new ArrayList<>();
//		listOfAddresses.add(new DeliveryAddress());
//		return listOfAddresses;
    }
}
