package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.AddCommentRequest;
import com.alexandru.springbootecommerce.dto.CommentDto;
import com.alexandru.springbootecommerce.entity.Comment;

public interface CommentService {
    CommentDto addComment(AddCommentRequest request, String username);
    boolean deleteComment(Long id, String username);
    CommentDto updateComment(CommentDto comment, String username);
}
