package com.geekbrains.app.controllers;

import com.geekbrains.app.services.ShoppingCartService;
import com.geekbrains.app.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCartService shoppingCartService;

    private Double priceWs;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("priceWs")
    public Double getPriceWs() {
        return priceWs;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart-page";
    }

//    @GetMapping("/cart/product-delete/{id}")
//    public String deleteProductFromCart(@PathVariable("id") Long id, HttpSession httpSession) {
//        shoppingCartService.removeFromCart(httpSession, id);
//        return "redirect:/cart";
//    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long id, HttpSession httpSession) {
        shoppingCartService.removeFromCart(httpSession, id);
        return "redirect:/cart";
    }

    @MessageMapping("/pricews")
    @SendTo("/ws-broker/prices")
    public Double newPriceWs(HttpSession httpSession) {
       return priceWs = shoppingCartService.getTotalCost(httpSession);
    }

}
