package orders.controllers;

import contract.entities.Order;
import contract.entities.User;
import contract.utils.ShoppingCart;
import orders.services.OrderService;
import orders.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    public Order makeOrder(ShoppingCart cart, User user) {
        return orderService.makeOrder(cart, user);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public Order findById(Long id) {
        return orderService.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderService.saveOrder(order);
    }

    @Override
    public Order changeOrderStatus(Order order, Long statusId) {
        return orderService.changeOrderStatus(order, statusId);
    }
}
