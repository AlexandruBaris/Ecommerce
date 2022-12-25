package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.AddCommentRequest;
import com.alexandru.springbootecommerce.dto.CommentDto;
import com.alexandru.springbootecommerce.entity.Comment;
import com.alexandru.springbootecommerce.entity.Product;
import com.alexandru.springbootecommerce.entity.User;
import com.alexandru.springbootecommerce.exceptions.CommentNotFoundException;
import com.alexandru.springbootecommerce.exceptions.ProductNotFound;
import com.alexandru.springbootecommerce.exceptions.UserNotFoundException;
import com.alexandru.springbootecommerce.repository.CommentRepository;
import com.alexandru.springbootecommerce.repository.ProductRepository;
import com.alexandru.springbootecommerce.repository.UserRepository;
import com.alexandru.springbootecommerce.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDto addComment(AddCommentRequest request, String username) {
        User user = getCurrentUser(username);
        Product product = getProduct(request.getProductId());


        Comment comment = Comment.builder()
                .text(request.getText())
                .date(LocalDate.now())
                .build();

        comment.setProduct(product);
        comment.setUser(user);
        user.getComments().add(comment);

        return CommentDto.fromComment(commentRepository.save(comment));
    }

    //TO DO
    @Transactional
    @Override
    public boolean deleteComment(Long commentId, String username) {
        User user = getCurrentUser(username);
        commentRepository.deleteById(commentId);
        userRepository.save(user);
        return true;
    }

    @Override
    public CommentDto updateComment(CommentDto comment, String username) {
        return null;
    }

    private User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
    }

    private Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product with id " + id + " was not found"));
    }

    private Comment getComment(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + " was not found"));
    }
}
