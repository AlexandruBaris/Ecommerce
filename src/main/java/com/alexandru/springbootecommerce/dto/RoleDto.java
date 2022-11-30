package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String role;

    public static RoleDto fromRole(Role role){
        return RoleDto.builder()
                .role(role.getRole_name())
                .build();
    }
}
