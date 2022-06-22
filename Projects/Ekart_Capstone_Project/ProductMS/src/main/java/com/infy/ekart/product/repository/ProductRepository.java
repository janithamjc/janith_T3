package com.infy.ekart.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.product.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
