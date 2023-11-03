package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByPaymentIdAndDeletedAtIsNull(Integer paymentId);

}
