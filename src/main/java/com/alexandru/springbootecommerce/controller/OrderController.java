package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.repository.OrderRepository;
import com.alexandru.springbootecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/status/{id}")
    public ResponseEntity<?> getAllOrdersByStatus(Authentication authentication, @PathVariable Long id){
        return ResponseEntity.ok(service.findAllOrdersByStatus(authentication.getName(), id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> addOrder(@RequestBody List<ProductDetails> products, Authentication authentication) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(service.addOrder(products, authentication.getName()));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id, Authentication authentication){
        return service.cancelOrder(id, authentication.getName()) ? ResponseEntity.ok(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
