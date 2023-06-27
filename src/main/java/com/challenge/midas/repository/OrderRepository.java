package com.challenge.midas.repository;

import com.challenge.midas.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE " +
            "o.ordenNumber LIKE :value " +
            "AND o.deleted = false " +
            "ORDER BY o.ordenNumber ASC")
    List<Order> getByValue(String value);

    @Query("select o from Order o where o.user.id = ?1")
    List<Order> getOrderByUser(String idUser);

    @Query("SELECT o FROM Order o WHERE o.deleted = false ORDER BY o.ordenNumber ASC")
    List<Order> getByEnable();

    @Query("SELECT o FROM Order o WHERE o.deleted = true ORDER BY o.ordenNumber ASC")
    List<Order> getByDisable();
}