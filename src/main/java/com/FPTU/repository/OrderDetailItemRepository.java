package com.FPTU.repository;

import com.FPTU.model.OrderDetailItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailItemRepository extends JpaRepository<OrderDetailItem, Long> {
}