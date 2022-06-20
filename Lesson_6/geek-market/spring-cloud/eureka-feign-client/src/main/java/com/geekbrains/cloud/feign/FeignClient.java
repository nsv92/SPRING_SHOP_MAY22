package com.geekbrains.cloud.feign;

import com.geekbrains.cloud.client.product.Product;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient("geek-spring-cloud-eureka-feign-client")
public interface FeignClient {

    @RequestMapping("/products")
    List<Product> getProduct();
}
