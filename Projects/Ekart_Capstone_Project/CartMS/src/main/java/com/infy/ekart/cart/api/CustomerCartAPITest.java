package com.infy.ekart.cart.api;

import com.infy.ekart.cart.dto.CartProductDTO;
import com.infy.ekart.cart.dto.CustomerCartDTO;
import com.infy.ekart.cart.dto.ProductDTO;
import com.infy.ekart.cart.exception.EKartCustomerCartException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerCartAPITest {

    @Autowired
    private CustomerCartAPI customerCartAPI;

    @Test
    void addProductToCart() throws EKartCustomerCartException {
        //mock obj
        CustomerCartDTO customerCartDTO = new CustomerCartDTO();

        customerCartDTO.setCustomerEmailId("harry@infosys.com");
        Set<CartProductDTO> cartProductDTOS = new HashSet<>();
        CartProductDTO cartProductDTO = new CartProductDTO();
        //product 01
        cartProductDTO.setQuantity(1);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1000);
        cartProductDTO.setProduct(productDTO);
        customerCartDTO.setCartProducts(cartProductDTOS);
        String response = customerCartAPI.addProductToCart(customerCartDTO).getBody();
        assertTrue(response.contains("The products are successfully"));

    }

    @Test
    void getProductsFromCart() throws EKartCustomerCartException {
        //input
        String emailId = "harry@infosys.com";
        //set of products which received fron cart product dto
       Set<CartProductDTO> cartProductDTOS = customerCartAPI.
               getProductsFromCart(emailId,0,10).getBody();
        assertTrue(cartProductDTOS.size()>0);
    }

    @Test
    void deleteProductFromCart() throws EKartCustomerCartException {
        int  productId = 1000; String emailId = "harry@infosys.com";

        Optional<CartProductDTO>  checkBefore = customerCartAPI.getProductsFromCart(emailId,0,10).getBody().
                stream().filter(x->x.getProduct().getProductId()==1000).findFirst();

//        String emailId = "harry@infosys.com";
//        //set of products which received frm cart product dto
//         customerCartAPI.
//                deleteProductFromCart(emailId,productId).getBody();
        if(checkBefore.isPresent()) {
            customerCartAPI.deleteProductFromCart(emailId, productId);
        }
        Optional<CartProductDTO>  afterDel = customerCartAPI.getProductsFromCart(emailId,0,10).getBody().
                stream().filter(x->x.getProduct().getProductId()==1000).findFirst();
       assertFalse(afterDel.isPresent());

    }

    @Test
    void modifyQuantityOfProductInCart() {
    }

    @Test
    void deleteAllProductsFromCart() {
    }
}