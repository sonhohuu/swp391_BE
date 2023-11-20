package com.FPTU.repository;

import com.FPTU.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Long> {
  List<ItemCategory> findAll();
}
