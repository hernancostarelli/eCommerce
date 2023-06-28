package com.challenge.midas.repository;

import com.challenge.midas.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    @Query("SELECT s FROM ShoppingCart s WHERE " +
            "s.user.name LIKE :value " +
            "AND s.user.surname LIKE :value " +
            "AND s.deleted = false " +
            "ORDER BY s.user.surname ASC")
    List<ShoppingCart> getByValue(String value);

    @Query("select s from ShoppingCart s where s.user.id = ?1")
    List<ShoppingCart> getShoppingCartLByUser(String idUser);

    @Query("SELECT s FROM ShoppingCart s WHERE s.deleted = false ORDER BY s.user.surname ASC")
    List<ShoppingCart> getByEnable();

    @Query("SELECT s FROM ShoppingCart s WHERE s.deleted = true ORDER BY s.user.surname ASC")
    List<ShoppingCart> getByDisable();
}