package com.geekbrains.cloud.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ProductController {
    @RequestMapping("/greeting")
    String greeting();

    @RequestMapping("/product/{id}")
    String product(@PathVariable Long id);
}
