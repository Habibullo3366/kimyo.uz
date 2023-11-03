package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findByOrdersAndDeletedAtIsNull(Integer orderId);

    Optional<Orders> findByOrdersAndDeletedAtIsNull(String fileName);

}
