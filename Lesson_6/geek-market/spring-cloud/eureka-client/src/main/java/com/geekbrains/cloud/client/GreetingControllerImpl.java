package com.geekbrains.cloud.client;


import com.geekbrains.cloud.client.product.Product;
import com.geekbrains.cloud.client.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingControllerImpl implements ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String greeting() {
        return "this eureka client";
    }

    @Override
    public String product(@PathVariable Long id) {
        return productService.getProductById(id).toString();
    }

    @Override
    public List<Product> Products() {
        return productService.getAllProducts();
    }


}
