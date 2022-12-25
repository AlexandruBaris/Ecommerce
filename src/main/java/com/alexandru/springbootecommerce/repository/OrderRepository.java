package com.alexandru.springbootecommerce.repository;

import com.alexandru.springbootecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByStatus_id(Long id);
//    List<Order> findAllByUser_id(Long id);

}
