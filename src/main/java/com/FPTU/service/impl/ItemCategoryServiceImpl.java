package com.FPTU.service.impl;

import com.FPTU.converter.ItemCategoryConverter;
import com.FPTU.dto.ItemCategoryDTO;
import com.FPTU.model.ItemCategory;
import com.FPTU.repository.ItemCategoryRepository;
import com.FPTU.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
  @Autowired
  private ItemCategoryRepository itemCategoryRepository;
  @Autowired
  private ItemCategoryConverter itemCategoryConverter;
  @Override
  public List<ItemCategoryDTO> getAllItemCategory() {
    List<ItemCategoryDTO> list = new ArrayList<>();
    for (ItemCategory i : itemCategoryRepository.findAll()) {
      ItemCategoryDTO iDTO = itemCategoryConverter.toDTO(i);
      list.add(iDTO);
    }
    return list;
  }
}