package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.*;
import com.geekbrains.geekmarketwinter.repositories.specifications.ProductSpecs;
import com.geekbrains.geekmarketwinter.services.*;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private MailService mailService;
    private UserService userService;
    private OrderService orderService;
    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private DeliveryAddressService deliverAddressService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setDeliverAddressService(DeliveryAddressService deliverAddressService) {
        this.deliverAddressService = deliverAddressService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public String shopPage(Model model, HttpSession httpSession,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "min", required = false) Double min,
                           @RequestParam(value = "max", required = false) Double max
    ) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
            filters.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }

        Page<Product> products = productService.getProductsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);

        model.addAttribute("products", products.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", products.getTotalPages());

        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);

        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);


        return "shop-page";
    }

    private final static String QUEUE_NAME = "hello";

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        shoppingCartService.addToCart(session, id);
//        Double price = shoppingCartService.getCurrentCart(session).getTotalCost();
        String referrer = httpServletRequest.getHeader("referer");

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()){
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String msg = "Hello World!";
//            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//            System.out.println("sent " + msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "redirect:" + referrer;
    }

//    @GetMapping("/order/fill")
//    public String orderFillPage(Model model, HttpSession httpSession) {
//        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
//        model.addAttribute("cart", cart);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUserName(authentication.getName());
//        Order order = orderService.makeOrder(cart, user);
//        model.addAttribute("order", order);
//        return "order-fill-page";
//    }

    /*@GetMapping через OrderDTO*/
//    @GetMapping("/order/fill")
//    public String orderFillPage(Model model, HttpSession httpSession) {
//        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
//        model.addAttribute("cart", cart);
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        OrderDTO orderDTO = orderService.makeOrderDTO(cart, userName);
//        model.addAttribute("orderDTO", orderDTO);
//        System.out.println(orderDTO.toString());
//        return "order-fill-page";
//    }

    @GetMapping("/order/fill")
    public String orderFillPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        model.addAttribute("deliveryInfo", deliveryInfo);
        return "order-fill-page";
    }

    @PostMapping("/order/confirm")
    public String orderConfirm(Model model, HttpServletRequest httpServletRequest,
                               @ModelAttribute(name = "deliveryInfo") DeliveryInfo deliveryInfo,
                               Principal principal) {
                if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()),
                user);
        order.setPhoneNumber(deliveryInfo.getPhoneNumber());
        order.setDeliveryAddress(deliverAddressService.save(user, deliveryInfo.getDeliveryAddress()));
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setDeliveryPrice(0.0);
        orderService.saveOrder(order);
        return "redirect:/";
    }

//    @PostMapping("/order/confirm")
//    public String orderConfirm(Model model, HttpServletRequest httpServletRequest,
//                               @ModelAttribute(name = "order") Order orderFromFrontend,
//                               Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        User user = userService.findByUserName(principal.getName());
//        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), user);
//        order.setDeliveryAddress(orderFromFrontend.getDeliveryAddress());
//        order.setPhoneNumber(orderFromFrontend.getPhoneNumber());
//        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
//        order.setDeliveryPrice(0.0);
//        order = orderService.saveOrder(order);
//        model.addAttribute("order", order);
//        return "order-filler";
//    }

    @GetMapping("/order/result/{id}")
    public String orderConfirm(Model model, @PathVariable(name = "id") Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        // todo ждем до оплаты, проверка безопасности и проблема с повторной отправкой письма сделать одноразовый вход
        User user = userService.findByUserName(principal.getName());
        Order confirmedOrder = orderService.findById(id);
        if (!user.getId().equals(confirmedOrder.getUser().getId())) {
            return "redirect:/";
        }
        mailService.sendOrderMail(confirmedOrder);
        model.addAttribute("order", confirmedOrder);
        return "order-result";
    }
}
