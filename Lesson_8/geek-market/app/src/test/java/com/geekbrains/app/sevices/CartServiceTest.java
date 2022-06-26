package com.geekbrains.app.sevices;

import com.geekbrains.app.entites.Product;
import com.geekbrains.app.services.ProductService;
import com.geekbrains.app.services.ShoppingCartService;
import com.geekbrains.app.utils.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {

    ShoppingCartService cartService;

    @MockBean
    ProductService mockProductService;

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
    public void getCurrentCartTest() {
        ShoppingCart currentCart = cartService.getCurrentCart(mockSession);
        System.out.println(currentCart.toString());
        Assert.assertEquals(testProduct, currentCart.getItems().get(0).getProduct());
    }

    @Test
    public void setProductCountTest() throws Exception {
        cartService.setProductService(mockProductService);
        when(mockProductService.getProductById(testProduct.getId())).thenReturn(testProduct);
        cartService.setProductCount(mockSession, testProduct.getId(), 123L);
        System.out.println(cartService.getCurrentCart(mockSession).toString());
        Assert.assertEquals(123L,
                cartService.getCurrentCart(mockSession).getQuantity(testProduct).longValue());
    }

}
