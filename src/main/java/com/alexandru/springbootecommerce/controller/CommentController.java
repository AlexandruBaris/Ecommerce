package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.AddCommentRequest;
import com.alexandru.springbootecommerce.dto.CommentDto;
import com.alexandru.springbootecommerce.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;

    @PostMapping("/save")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request, Authentication authentication){
        return ResponseEntity.ok(service.addComment(request, authentication.getName()));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteComment(@PathVariable Long id, Authentication authentication){
        return service.deleteComment(id, authentication.getName()) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }
}
