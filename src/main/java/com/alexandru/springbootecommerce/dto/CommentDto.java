package com.alexandru.springbootecommerce.dto;

import com.alexandru.springbootecommerce.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentDto {

    private Long id;
    private LocalDate date;
    private String text;
    private Long userId;
    private Long productId;


    public static CommentDto fromComment(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .text(comment.getText())
                .userId(comment.getUser().getUserId())
                .productId(comment.getProduct().getProductId())
                .build();
    }
}
