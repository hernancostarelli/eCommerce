package com.challenge.midas.repository;

import com.challenge.midas.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE " +
            "o.user.surname LIKE :value " +
            "AND o.user.surname LIKE :value " +
            "AND o.deleted = false " +
            "ORDER BY o.user.surname ASC")
    List<Order> getByValue(String value);

    @Query("select o from Order o where o.user.id = ?1")
    List<Order> getOrderByUser(String idUser);

    @Query("SELECT o FROM Order o WHERE o.deleted = false ORDER BY o.ordenNumber ASC")
    List<Order> getByEnable();

    @Query("SELECT o FROM Order o WHERE o.deleted = true ORDER BY o.ordenNumber ASC")
    List<Order> getByDisable();
}