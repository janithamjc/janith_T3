package com.infy.ekart.customer.api;

import com.infy.ekart.customer.dto.*;
import org.junit.jupiter.api.Assertions;
import com.infy.ekart.customer.exception.EKartCustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerAPITest {


    @Autowired
    private CustomerAPI customerAPI;

    @Test
    void authenticateCustomer() throws EKartCustomerException {
        //mock obj
        CustomerLoginDTO dto = new CustomerLoginDTO();

        dto.setEmailId("janith@gmail.com");dto.setPassword("1X31@4rwweWd");
        CustomerDTO customerDTO = customerAPI.authenticateCustomer(dto).getBody();
        assertTrue(customerDTO!=null);
        assertTrue(!customerDTO.getName().equals(""));

    }

    @Test
    void registerCustomer() throws EKartCustomerException {
        CustomerDTO customerDTO = new CustomerDTO();
        long currentTimeMills = System.currentTimeMillis();
        String keyChar = (currentTimeMills+"").substring(3,(currentTimeMills+"").length());
        System.out.println(keyChar);
        customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
        customerDTO.setName("janith c");
        customerDTO.setPassword("1X31@4rwweWd");
        customerDTO.setPhoneNumber((currentTimeMills+"").substring(0,10));
        customerDTO.setAddress("sample address_ "+keyChar);
        String status =  customerAPI.registerCustomer(customerDTO).getBody();
        assertTrue(status.contains("You are successfully registered"));
    }

//Verify that an error message is displayed if all the details are not filled
@Test
void registerCustomer_AllDetailsNotFilled() throws EKartCustomerException {
        //empty body
        CustomerDTO customerDTO = new CustomerDTO();

    ConstraintViolationException thrown = assertThrows(
            ConstraintViolationException.class,
            () -> customerAPI.registerCustomer(customerDTO));
    System.out.println(thrown.getMessage());
    assertTrue(thrown.getMessage().contains("email id is mandatory"));
    assertTrue(thrown.getMessage().contains("address is required"));
    assertTrue(thrown.getMessage().contains("The Password is mandatory"));
}
//    Verify that an error message is displayed if the email id is already in use by some other user
@Test
void registerCustomer_emailExist() throws EKartCustomerException {
    CustomerDTO customerDTO = new CustomerDTO();
    long currentTimeMills = System.currentTimeMillis();
    String keyChar = (currentTimeMills+"").substring(3,(currentTimeMills+"").length());
//    System.out.println(keyChar);
    customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
    customerDTO.setName("janith c");
    customerDTO.setPassword("1X31@4rwweWd");
    customerDTO.setPhoneNumber((currentTimeMills+"").substring(0,10));
    customerDTO.setAddress("sample address_ "+keyChar);
    String status =  customerAPI.registerCustomer(customerDTO).getBody();
    assertTrue(status.contains("You are successfully registered"));

    // re - register same customer
    EKartCustomerException thrown = assertThrows(
            EKartCustomerException.class,
            () -> customerAPI.registerCustomer(customerDTO));
        assertTrue(thrown.getMessage().contains("EMAIL_ID_ALREADY_IN_USE"));
}

//    Verify that an error message is displayed if the name contains numbers or special characters
@Test
void registerCustomer_NameContainsSpecialChar() throws EKartCustomerException {
    CustomerDTO customerDTO = new CustomerDTO();
    long currentTimeMills = System.currentTimeMillis();
    String keyChar = (currentTimeMills+"").substring(3,(currentTimeMills+"").length());
//    System.out.println(keyChar);
    customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
    customerDTO.setName("janith c@");
    customerDTO.setPassword("1X31@4rwweWd");
    customerDTO.setPhoneNumber((currentTimeMills+"").substring(0,10));
    customerDTO.setAddress("sample address_ "+keyChar);
    // re - register same customer
    ConstraintViolationException thrown = assertThrows(
            ConstraintViolationException.class,
            () -> customerAPI.registerCustomer(customerDTO));
    assertTrue(thrown.getMessage().contains("name is not in proper format"));
}
//    Password should be a combination of at least an uppercase alphabet, lowercase alphabets, a digit, and a special character. Verify for the same and display an error message if the password entered by the customer is not in the expected format
@Test
void registerCustomer_PasswordStrength() throws EKartCustomerException {
    CustomerDTO customerDTO = new CustomerDTO();
    long currentTimeMills = System.currentTimeMillis();
    String keyChar = (currentTimeMills+"").substring(3,(currentTimeMills+"").length());
//    System.out.println(keyChar);
    customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
    customerDTO.setName("janith c");
    customerDTO.setPassword("abcd");
    customerDTO.setPhoneNumber((currentTimeMills+"").substring(0,10));
    customerDTO.setAddress("sample address_ "+keyChar);
    // re - register same customer
    ConstraintViolationException thrown = assertThrows(
            ConstraintViolationException.class,
            () -> customerAPI.registerCustomer(customerDTO));
    assertTrue(thrown.getMessage().contains("Password should have at least one upper case"));
    assertTrue(thrown.getMessage().contains("Password should have at least one number"));
    assertTrue(thrown.getMessage().contains("Password should have at least one special character"));

}
//    Verify that an error message is displayed if the phone number is already in use by some other user
@Test
void registerCustomer_duplicatePhoneNumber() throws EKartCustomerException {
    CustomerDTO customerDTO = new CustomerDTO();
    long currentTimeMills = System.currentTimeMillis();
    String keyChar = (currentTimeMills+"").substring(3,(currentTimeMills+"").length());

    customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
    customerDTO.setName("janith c");
    customerDTO.setPassword("1X31@4rwweWd");
    customerDTO.setPhoneNumber(keyChar);
    customerDTO.setAddress("sample address_ "+keyChar);
    String status =  customerAPI.registerCustomer(customerDTO).getBody();
    assertTrue(status.contains("You are successfully registered"));

      keyChar = (currentTimeMills+"1").substring(3,(currentTimeMills+"1").length());
    customerDTO.setEmailId("janith@gmailw"+keyChar+".com");
    customerDTO.setName("janith c");
    customerDTO.setPassword("1X31@4rwweWd");
   // customerDTO.setPhoneNumber((currentTimeMills+"").substring(0,10));
    customerDTO.setAddress("sample address_ "+keyChar);

    // re - register same customer
    EKartCustomerException thrown = assertThrows(
            EKartCustomerException.class,
            () -> customerAPI.registerCustomer(customerDTO));
    assertTrue(thrown.getMessage().contains("PHONE_NUMBER_ALREADY_IN_USE"));
}

    @Test
    void addProductToCart_default() throws EKartCustomerException {

        CustomerCartDTO dto = new CustomerCartDTO();
            dto.setCustomerEmailId("harry@infosys.com");
        Set<CartProductDTO> cartProductDTOS = new HashSet<>();

        CartProductDTO cartProductDTO = new CartProductDTO();
        //product 01
        cartProductDTO.setQuantity(5);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1000);
        cartProductDTO.setProduct(productDTO);
        //product 02
        cartProductDTO.setQuantity(12);
        ProductDTO productDTO2 = new ProductDTO();
        productDTO.setProductId(1009);
        cartProductDTO.setProduct(productDTO2);

            dto.setCartProducts(cartProductDTOS);
            String response = customerAPI.addProductToCart(dto).getBody();
         Assertions.assertTrue(response.contains("The products are successfully added to the cart"));
    }

    //    Only customers should be provided with this feature
//        Verify if the same product is added again. If so, the item in the cart should be
//        updated with the quantity (existing quantity + the new quantity)
//        Display proper messages in case of errors or exceptions

}