package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.dto.ProductDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {
    List<OrderDto> findAllOrders(String username);
    OrderDto addOrder(List<ProductDetails> products, String username) throws MessagingException, UnsupportedEncodingException;
    boolean cancelOrder(Long id, String username);
}
