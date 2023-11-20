package com.FPTU.converter;

import com.FPTU.dto.ItemCategoryDTO;
import com.FPTU.model.ItemCategory;
import org.springframework.stereotype.Component;

@Component
public class ItemCategoryConverter {
  public ItemCategoryDTO  toDTO(ItemCategory itemCategory){
    ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
    itemCategoryDTO.setId(itemCategory.getCategoryId());
    itemCategoryDTO.setName(itemCategory.getName());
    return itemCategoryDTO;
  }

}