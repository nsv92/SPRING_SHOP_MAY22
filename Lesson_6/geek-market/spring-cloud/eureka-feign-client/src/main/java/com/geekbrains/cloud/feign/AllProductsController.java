package com.geekbrains.cloud.feign;

import com.geekbrains.cloud.client.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/all-products")
public class AllProductsController {

    private FeignClient feignClient;

    @Autowired
    public void setFeignClient(FeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping
    public String allProducts(Model model) {
        List<Product> productsSource = feignClient.getProduct();
        Page<Product> products = new PageImpl<>(productsSource);
        model.addAttribute("products", products.getContent());
        return "all-products";
    }


}
