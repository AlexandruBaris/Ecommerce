package com.alexandru.springbootecommerce.repository;

import com.alexandru.springbootecommerce.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
