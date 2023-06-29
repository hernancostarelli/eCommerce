package com.challenge.midas.repository;

import com.challenge.midas.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("SELECT p FROM Payment p WHERE " +
            "p.user.name LIKE :value " +
            "AND p.user.surname LIKE :value " +
            "AND p.paymentDate LIKE :value " +
            "AND p.deleted = false " +
            "ORDER BY p.user.surname ASC")
    List<Payment> getByValue(String value);

    @Query("select p from Payment p where p.user.id = ?1")
    List<Payment> getPaymentByUser(String idUser);

    @Query("SELECT p FROM Payment p WHERE p.deleted = false ORDER BY p.user.surname ASC")
    List<Payment> getByEnable();

    @Query("SELECT p FROM Payment p WHERE p.deleted = true ORDER BY p.user.surname ASC")
    List<Payment> getByDisable();
}