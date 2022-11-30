package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.ProductDto;
import com.alexandru.springbootecommerce.entity.Product;
import com.alexandru.springbootecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(service.findAllProducts());
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product){
        return ResponseEntity.ok(service.addProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id){
        return service.deleteProductById(id) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product){
        return ResponseEntity.ok(service.updateProduct(product));
    }
}
