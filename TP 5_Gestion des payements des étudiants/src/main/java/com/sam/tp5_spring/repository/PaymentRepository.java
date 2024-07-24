package com.sam.tp5_spring.repository;

import com.sam.tp5_spring.entities.Payment;
import com.sam.tp5_spring.entities.PaymentStatus;
import com.sam.tp5_spring.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
