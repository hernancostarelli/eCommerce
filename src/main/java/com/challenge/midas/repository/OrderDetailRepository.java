package com.challenge.midas.repository;

import com.challenge.midas.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    @Query("SELECT o FROM OrderDetail o WHERE " +
            "o.order LIKE :value " +
            "AND o.product LIKE :value " +
            "AND o.deleted = false " +
            "ORDER BY o.order.ordenNumber ASC")
    List<OrderDetail> getByValue(String value);

    @Query("SELECT o FROM OrderDetail o WHERE o.deleted = false ORDER BY o.order.ordenNumber ASC")
    List<OrderDetail> getByEnable();

    @Query("SELECT o FROM OrderDetail o WHERE o.deleted = true ORDER BY o.order.ordenNumber ASC")
    List<OrderDetail> getByDisable();
}