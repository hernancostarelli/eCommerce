package com.challenge.midas.repository;

import com.challenge.midas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE :value " +
            "AND p.deleted = false " +
            "ORDER BY p.name ASC")
    List<Product> getByValue(String value);

    @Query("SELECT p FROM Product p WHERE p.deleted = false ORDER BY p.name ASC")
    List<Product> getByEnable();

    @Query("SELECT p FROM Product p WHERE p.deleted = true ORDER BY p.name ASC")
    List<Product> getByDisable();
}