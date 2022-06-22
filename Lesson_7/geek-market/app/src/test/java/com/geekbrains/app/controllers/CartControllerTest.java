package com.geekbrains.app.controllers;

import com.geekbrains.app.entites.Product;
import com.geekbrains.app.services.ShoppingCartService;
import com.geekbrains.app.utils.ShoppingCart;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ShoppingCartService cartService;

    Product testProduct = new Product(5L, "UnitTestProduct");
    ShoppingCart testCart = new ShoppingCart();

    HttpSession mockSession = new MockHttpSession();

    @Before
    public void init() {
        cartService = new ShoppingCartService();
        testCart.add(testProduct);
        mockSession.setAttribute("cart", testCart);
    }

    @Test
    public void getCartPageTest() throws Exception {
        mockMvc.perform(get("/cart").session((MockHttpSession) mockSession))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("UnitTestProduct")));
    }

}
