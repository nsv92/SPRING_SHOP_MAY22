package com.geekbrains.app.services;

import com.geekbrains.app.entites.Order;
import com.geekbrains.app.entites.OrderDTO;
import com.geekbrains.app.entites.OrderItem;
import com.geekbrains.app.entites.User;
import com.geekbrains.app.repositories.OrderRepository;
import com.geekbrains.app.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private OrderStatusService orderStatusService;

    private ShoppingCartService shoppingCartService;

    private UserServiceImpl userService;

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeliveryAddressService(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        order.setStatus(orderStatusService.getStatusById(1L));
        order.setPrice(cart.getTotalCost());
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }
        return order;
    }

    @Transactional
    public Order orderDTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        User user = userService.findByUserName(orderDTO.getUser());
        order.setUser(user);
        order.setStatus(orderStatusService.getStatusById(1L));
        order.setPrice(orderDTO.getPrice());
        order.setDeliveryPrice(orderDTO.getDeliveryPrice());
        order.setDeliveryAddress(deliveryAddressService.save(user, orderDTO.getDeliveryAddress()));
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setCreateAt(orderDTO.getCreateAt());
        order.setUpdateAt(orderDTO.getUpdateAt());
        order.setConfirmed(orderDTO.isConfirmed());
        return order;
    }

    @Transactional
    public OrderDTO makeOrderDTO(ShoppingCart cart, String user) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(0L);
        orderDTO.setUser(user);
        orderDTO.setStatus(orderStatusService.getStatusById(1L));
        orderDTO.setPrice(cart.getTotalCost());
        orderDTO.setOrderItems(new ArrayList<>(cart.getItems()));
        return orderDTO;
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order saveOrder(Order order) {
        Order orderOut = orderRepository.save(order);
        orderOut.setConfirmed(true);
        return orderOut;
    }

    public Order changeOrderStatus(Order order, Long statusId) {
        order.setStatus(orderStatusService.getStatusById(statusId));
        return saveOrder(order);
    }
}
