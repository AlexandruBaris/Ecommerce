package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private List<OrderDto> orders = new ArrayList<>();
    private Set<RoleDto> roles = new HashSet<>();

    public static UserDto fromUser(User user){
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        if(Objects.nonNull(user.getRoles())){
            userDto.setRoles(user.getRoles().stream()
                    .map(RoleDto::fromRole)
                    .collect(Collectors.toSet()));
        }

        if(Objects.nonNull(user.getOrders())){
            userDto.setOrders(user.getOrders().stream()
                    .map(OrderDto::fromOrder)
                    .collect(Collectors.toList()));
        }

        return userDto;
    }
}
