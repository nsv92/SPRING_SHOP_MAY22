package orders.controllers;

import contract.entities.OrderStatus;
import orders.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderStatusControllerImpl implements OrderStatusController{

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    public OrderStatus getStatusById(Long id) {
        return orderStatusService.getStatusById(id);
    }
}
