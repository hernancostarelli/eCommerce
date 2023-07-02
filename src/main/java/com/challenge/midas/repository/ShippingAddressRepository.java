package com.challenge.midas.repository;

import com.challenge.midas.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, String> {

    @Query("SELECT s FROM ShippingAddress s WHERE " +
            "s.street LIKE :value " +
            "AND s.zipCode LIKE :value " +
            "AND s.deleted = false " +
            "ORDER BY s.street ASC")
    List<ShippingAddress> getByValue(String value);

    @Query("select s from ShippingAddress s where s.user.id = ?1")
    List<ShippingAddress> getShippingAddressByUser(String idUser);

    @Query("SELECT s FROM ShippingAddress s WHERE s.deleted = false ORDER BY s.street ASC")
    List<ShippingAddress> getByEnable();

    @Query("SELECT s FROM ShippingAddress s WHERE s.deleted = true ORDER BY s.street ASC")
    List<ShippingAddress> getByDisable();
}