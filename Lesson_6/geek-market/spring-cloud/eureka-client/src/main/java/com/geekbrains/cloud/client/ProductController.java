package com.geekbrains.cloud.client;

import com.geekbrains.cloud.client.product.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ProductController {
    @RequestMapping("/greeting")
    String greeting();

    @RequestMapping("/product/{id}")
    String product(@PathVariable Long id);

    @RequestMapping("/products")
    List<Product> Products();
}
