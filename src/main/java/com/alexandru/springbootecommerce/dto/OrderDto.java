package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDate date;
    private Double amount;
    private StatusDto status;
    private List<ProductDto> products = new ArrayList<>();


    public static OrderDto fromOrder(Order order){
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .amount(order.getAmount())
                .build();

        if(Objects.nonNull(order.getStatus())){
            orderDto.setStatus(StatusDto.fromStatus(order.getStatus()));
        }

        if(Objects.nonNull(order.getProducts())){
            orderDto.setProducts(order.getProducts().stream()
                    .map(ProductDto::fromProduct)
                    .collect(Collectors.toList()));
        }
         return orderDto;
    }
}
