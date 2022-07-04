package orders.controllers;

import contract.entities.Order;
import contract.entities.User;
import contract.utils.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderController {

    @PostMapping("/makeOrder")
    public Order makeOrder(@RequestBody ShoppingCart cart, @RequestBody User user);

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders();

    @GetMapping("/getOrderById")
    public Order findById(@RequestParam("id") Long id);

    @PostMapping("/saveOrder")
    public Order saveOrder(@RequestBody Order order);

    @PostMapping("/changeOrderStatus")
    public Order changeOrderStatus(@RequestBody Order order, @RequestParam("statusId") Long statusId);
}
