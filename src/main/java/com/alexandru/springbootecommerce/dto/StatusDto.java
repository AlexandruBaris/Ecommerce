package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private Long id;
    private String status_name;

    public static StatusDto fromStatus(Status status){
        return StatusDto.builder()
                .id(status.getId())
                .status_name(status.getName())
                .build();
    }
}
