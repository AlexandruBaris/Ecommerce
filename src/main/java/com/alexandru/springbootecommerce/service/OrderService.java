package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.dto.ProductDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllOrders();
    OrderDto addOrder(List<ProductDetails> products);
}
