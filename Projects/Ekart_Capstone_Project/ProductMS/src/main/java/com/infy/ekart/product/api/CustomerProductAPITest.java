package com.infy.ekart.product.api;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.exception.EKartProductException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerProductAPITest {
    Logger log = LoggerFactory.getLogger(CustomerProductAPITest.class);
    @Autowired
    CustomerProductAPI customerProductAPI;
    @Test
    void getAllProductsDefault() throws EKartProductException {

        ResponseEntity responseEntity = customerProductAPI.getAllProducts(0,10);
        List<ProductDTO> list = (List<ProductDTO>) responseEntity.getBody();
//        for (ProductDTO product:list) {
          //  log.info(product.toString());
//        }
        //check count =10
        Assertions.assertEquals(list.size(),10);
    }

    @Test
    void getAllProductInvalidSize() throws EKartProductException{

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> customerProductAPI.getAllProducts(2,-1),
                "Expected doThing() to throw, but it didn't"
        );
        System.out.println(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Page size must not be less than one!"));
    }

    @Test
    void getAllProductsOutofRange() throws EKartProductException{
        //out of range page number should get zero results
        ResponseEntity responseEntity2 = customerProductAPI.getAllProducts(20,10);
        List<ProductDTO> list2 = (List<ProductDTO>) responseEntity2.getBody();
        for (ProductDTO product:list2) {
            log.info(product.toString());
        }
        Assertions.assertEquals(list2.size(),0);
    }

    @Test
    void getProductById() throws EKartProductException {
        ResponseEntity responseEntity = customerProductAPI.getProductById(1000);
        ProductDTO productDTO = (ProductDTO) responseEntity.getBody();
        assertTrue(productDTO!=null);
        assertEquals(productDTO.getProductId(),1000);
    }
}