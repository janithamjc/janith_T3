package com.infy.ekart.product.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.service.CustomerProductService;

@CrossOrigin
@RestController
@RequestMapping(value = "/product-api")
@Validated
public class CustomerProductAPI {

	@Autowired
	private CustomerProductService customerProductService;

	@Autowired
	private Environment environment;

	Log logger = LogFactory.getLog(CustomerProductAPI.class);

	// Get all the product details by calling getAllProducts() of
	// CustomerProductService and return the same
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
														   @RequestParam(defaultValue = "10") Integer pageSize) throws EKartProductException {
		return  ResponseEntity.ok().body(customerProductService.getAllProducts(pageNo,pageSize));
	}

	// Get the specific product by calling getProductById() of
	// CustomerProductService and return the same
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) throws EKartProductException {
		return  ResponseEntity.ok().body(customerProductService.getProductById(productId));
	}

//reduce the available quantity of product by calling reduceAvailableQuantity() of CustomerProductService and return the message with property as ProductAPI.REDUCE_QUANTITY_SUCCESSFULL
	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<String> reduceAvailableQuantity(@PathVariable Integer productId,
			@RequestBody Integer quantity) throws EKartProductException {
		// write your logic here
		try {
			customerProductService.reduceAvailableQuantity(productId,quantity);
			return ResponseEntity.ok().body("Success");
		}catch (Exception e){
			return ResponseEntity.internalServerError().body("Message:"+e.getMessage());
		}



	}
}
