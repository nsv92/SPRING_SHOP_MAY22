package com.geekbrains.geekmarketwinter.utils;

import com.geekbrains.geekmarketwinter.services.ShoppingCartService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AppLoggingAspect {

    private ShoppingCartService shoppingCartService;
    private HttpServletRequest httpServletRequest;

    private Map<String, Long> services = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @After("execution(public void com.geekbrains.geekmarketwinter.services.ShoppingCartService.addToCart(..))") // pointcut expression
    public void aopSimpleMethod() {
        System.out.println("добален товар в корзину");
    }

    @After("execution(* com.geekbrains.geekmarketwinter.services.ProductService.getProductsWithPagingAndFiltering(..))")
    private void getProductsLogger() {
        System.out.println("Получен список продуктов");
    }

    @After("execution(* com.geekbrains.geekmarketwinter.services.ProductService.getProductById(..))")
    private void getProductByIdLogger() {
        System.out.println("Получен продукт по id");
    }

    @After("execution(* com.geekbrains.geekmarketwinter.services.ProductService.save(..))")
    private void saveProductLogger() {
        System.out.println("Сохранен новый продукт");
    }

    @After("execution(* com.geekbrains.geekmarketwinter.services.ShoppingCartService." +
            "addToCart(..))")
    private void cartRecalculate() {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpServletRequest.getSession());
        System.out.println("Корзина пересчитана, новая цена: " + cart.getTotalCost());
    }

    @Around("execution(public * com.geekbrains.geekmarketwinter.services.*Service.*(..))")
    public Object aopMethodTimeMeter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        System.out.println("Start Service");
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        String[] tokens = proceedingJoinPoint.getSignature().getDeclaringTypeName().split("\\.");
        String serviceName = tokens[tokens.length - 1];
        long duration = end - begin;
        if (services.containsKey(serviceName)) {
            services.put(serviceName, services.get(serviceName) + duration);
        } else {
            services.put(serviceName, duration);
            for (String key : services.keySet()) {
                System.out.println(key + ": Время работы " + services.get(key) + "ms");
            }
            System.out.println("End Service");
        }
        return result;
    }


}
