package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.ShoppingCartService;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
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

}
