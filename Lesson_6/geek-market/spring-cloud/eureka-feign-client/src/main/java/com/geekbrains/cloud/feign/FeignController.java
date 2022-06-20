package com.geekbrains.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FeignController {

    private FeignClient feignClient;

    @Autowired
    public void setFeignClient(FeignClient feignClient) {
        this.feignClient = feignClient;
    }


}
