package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.services.OrderService;
import com.geekbrains.geekmarketwinter.services.OrderStatusService;
import com.geekbrains.geekmarketwinter.services.ShoppingCartService;
import com.geekbrains.geekmarketwinter.services.UserServiceImpl;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private OrderStatusService orderStatusService;

    private ShoppingCartService shoppingCartService;

    private UserServiceImpl userService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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

//    @GetMapping("/fill")
//    public String orderFillPage(Model model, HttpSession httpSession) {
//        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUserName(authentication.getName());
//        Order order = orderService.makeOrder(cart, user);
//        model.addAttribute("order", order);
//        return "order-fill-page";
//    }
}
