package com.FPTU.repository;

import com.FPTU.model.Course;
import com.FPTU.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    @Query(value = "SELECT * FROM item WHERE name LIKE %:name%", nativeQuery = true)
    List<Item> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT i.* from item i\n" +
            "join order_detail_item od\n" +
            "on od.item_id = i.item_id\n" +
            "join order_item o\n" +
            "on o.order_id = od.order_id\n" +
            "where o.order_id = :order_id", nativeQuery = true)
    List<Item> findItemByOrderId(@Param("order_id") Long id);
}
