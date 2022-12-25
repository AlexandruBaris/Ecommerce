package com.alexandru.springbootecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddCommentRequest {

    private Long productId;
    private String text;

}
