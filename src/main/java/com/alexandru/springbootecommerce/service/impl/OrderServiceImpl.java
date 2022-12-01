package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.OrderDto;
import com.alexandru.springbootecommerce.dto.ProductDetails;
import com.alexandru.springbootecommerce.entity.Order;
import com.alexandru.springbootecommerce.entity.Product;
import com.alexandru.springbootecommerce.entity.Status;
import com.alexandru.springbootecommerce.entity.User;
import com.alexandru.springbootecommerce.exceptions.OrderNotFound;
import com.alexandru.springbootecommerce.exceptions.ProductOutOfStock;
import com.alexandru.springbootecommerce.exceptions.StatusNotFound;
import com.alexandru.springbootecommerce.exceptions.UserNotFoundException;
import com.alexandru.springbootecommerce.repository.OrderRepository;
import com.alexandru.springbootecommerce.repository.ProductRepository;
import com.alexandru.springbootecommerce.repository.StatusRepository;
import com.alexandru.springbootecommerce.repository.UserRepository;
import com.alexandru.springbootecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final JavaMailSender mailSender;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final ProductRepository productRepository;

    private static final long ORDER_STATUS_CREATED = 1;
    private static final long ORDER_STATUS_CANCELED = 4;

    @Override
    public List<OrderDto> findAllOrders(String username) {
        User user = getCurrentUser(username);
        return user.getOrders().stream()
                .map(OrderDto::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(List<ProductDetails> productsDto, String username) throws MessagingException, UnsupportedEncodingException {

        User user = getCurrentUser(username);
        List<Product> products = getProducts(productsDto);
        Status status = getStatus(ORDER_STATUS_CREATED);
        Double amount = products.stream().mapToDouble(Product::getPrice).sum();

        Order order = Order.builder()
                .amount(amount)
                .date(LocalDate.now())
                .build();

        order.setUser(user);
        order.setStatus(status);
        order.addProduct(products);

        OrderDto orderDto = OrderDto.fromOrder(orderRepository.save(order));
        sendConfirmationEmail(user,orderDto);

        return orderDto;
    }

    @Override
    public boolean cancelOrder(Long id, String username) {
        User user = getCurrentUser(username);
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFound("Order not found"));
        if(user.getOrders().contains(order)){
            if(Objects.equals(order.getStatus().getName(), "CREATED") || Objects.equals(order.getStatus().getName(), "PROCESSING")){
                Status status = getStatus(ORDER_STATUS_CANCELED);
                order.setStatus(status);
                orderRepository.save(order);
                return true;
            }
        }
        return false;
    }


    private void add(List<Product> products, Order order){
        for(Product product : products){
            order.addProduct(product);
        }
    }

    private List<Product> getProducts(List<ProductDetails> productsDto){
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

    private void sendConfirmationEmail(User user, OrderDto order)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getUsername();
        String fromAddress = "alex.online.ecommerce@gmail.com";
        String senderName = "Ecommerce";
        String subject = "Order Number: " + order.getId();
        String content = "Dear [[name]],<br>"
                + "Your order has been created successfully."
                + "You can follow the status of the order in the personal cabinet on the website."
                + "Thank you,<br>"
                + "The Ecommerce team.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String fullName = user.getFirstName() + " " + user.getLastName();

        content = content.replace("[[name]]", fullName);

        helper.setText(content, true);

        mailSender.send(message);
    }

    private Status getStatus(Long id){
        return statusRepository.findById(id).orElseThrow(()-> new StatusNotFound("Status not found"));
    }

    private User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
    }

}
