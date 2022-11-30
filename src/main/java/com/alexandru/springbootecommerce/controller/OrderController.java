package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(service.findAllOrders());
    }

    @PostMapping("/save")
    //addProduct(RequestBody List<ProductDto> products, Auth auth)
    public ResponseEntity<?> addOrder(@RequestBody List<ProductDetails> products){
        return ResponseEntity.ok(service.addOrder(products));
    }

}
