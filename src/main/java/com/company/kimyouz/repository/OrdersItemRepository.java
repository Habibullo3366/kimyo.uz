package com.company.kimyouz.repository;

import com.company.kimyouz.entity.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem, Integer> {

    Optional<OrdersItem> findByOrdersItemIdAndDeletedAtIsNull(Integer orderItemId);

}
