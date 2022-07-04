package orders.controllers;

import contract.entities.OrderStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderStatusController {

    @GetMapping("/getOrderStatus")
    public OrderStatus getStatusById(@RequestParam("id") Long id);
}
