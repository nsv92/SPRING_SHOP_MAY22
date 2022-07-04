package market.services;

import contract.entities.Order;
import contract.entities.User;
import contract.utils.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service-client", contextId = "order-service")
public interface OrderService {

    @RequestMapping("/makeOrder")
    public Order makeOrder(@RequestBody ShoppingCart cart, @RequestParam String userName);

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders();

    @GetMapping("/getOrderById")
    public Order findById(@RequestParam("id") Long id);

    @RequestMapping("/saveOrder")
    public Order saveOrder(@RequestBody Order order);

    @RequestMapping("/changeOrderStatus")
    public Order changeOrderStatus(@RequestBody Order order, @RequestParam("statusId") Long statusId);
}

