package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.entity.Order;
import com.alexandru.springbootecommerce.entity.Product;
import com.alexandru.springbootecommerce.entity.Status;
import com.alexandru.springbootecommerce.exceptions.ProductOutOfStock;
import com.alexandru.springbootecommerce.exceptions.StatusNotFound;
import com.alexandru.springbootecommerce.repository.OrderRepository;
import com.alexandru.springbootecommerce.repository.ProductRepository;
import com.alexandru.springbootecommerce.repository.StatusRepository;
import com.alexandru.springbootecommerce.repository.UserRepository;
import com.alexandru.springbootecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final ProductRepository productRepository;

    private static final long ORDER_STATUS_CREATED = 1;

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(List<ProductDetails> productsDto) {

        List<Product> products = getProducts(productsDto);
        Status status = statusRepository.findById(ORDER_STATUS_CREATED).orElseThrow(()-> new StatusNotFound("Status not found"));
        Double amount = products.stream().mapToDouble(Product::getPrice).sum();

        Order order = Order.builder()
                .amount(amount)
                .date(LocalDate.now())
                .build();

        order.setUser(userRepository.findById(1L).get());
        order.setStatus(status);
        order.addProduct(products);

        return OrderDto.fromOrder(orderRepository.save(order));
    }


    public void add(List<Product> products, Order order){
        for(Product product : products){
            order.addProduct(product);
        }
    }

    public List<Product> getProducts(List<ProductDetails> productsDto){
        List<Product> products = new ArrayList<>();
        for(ProductDetails productDetails : productsDto){
            Product product = productRepository.findById(productDetails.getId()).orElseThrow();
            int remainingQuantity = product.getAvailableQuantity() - productDetails.getOrderedQuantity();
            if(remainingQuantity>= 0){
                product.setAvailableQuantity(remainingQuantity);
            }else{
                throw new ProductOutOfStock("Product " + product.getName() + " is out of stock");
            }
            products.add(product);
        }
        return products;
    }


}
