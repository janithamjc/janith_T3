package com.infy.ekart.cart.repository;

import java.util.List;
import java.util.Optional;

import com.infy.ekart.cart.entity.CartProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.cart.entity.CustomerCart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerCartRepository extends PagingAndSortingRepository<CustomerCart, Integer> {

	Optional<CustomerCart> findByCustomerEmailId(String customerEmailId);

	@Query(value = "SELECT a FROM CartProduct a WHERE a.CartId = ?1")
	List<CartProduct> getCartProductsByCartId(Integer cartId,Pageable pageable);
}
