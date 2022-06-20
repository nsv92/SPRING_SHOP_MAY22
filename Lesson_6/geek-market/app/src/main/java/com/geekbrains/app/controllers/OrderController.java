package com.geekbrains.app.controllers;

import com.geekbrains.app.services.OrderService;
import com.geekbrains.app.services.OrderStatusService;
import com.geekbrains.app.services.ShoppingCartService;
import com.geekbrains.app.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
