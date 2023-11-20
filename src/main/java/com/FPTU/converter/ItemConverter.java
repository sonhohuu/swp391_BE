package com.FPTU.converter;

import com.FPTU.dto.ItemDTO;
import com.FPTU.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {

  @Autowired
  private ItemCategoryConverter itemCategoryConverter;
  private final String defaultImg = "https://res.cloudinary.com/diaixspmb/image/upload/v1698942338/download.png.png";
  public Item toEntity(ItemDTO itemDTO){
    Item item = new Item();
    item.setName(itemDTO.getName());
    item.setDescription(itemDTO.getDescription());
    item.setPrice(itemDTO.getPrice());
    String img = (itemDTO.getImg() != null) ? itemDTO.getImg() : defaultImg;
    item.setImg(img);
    return item;
  }
  public ItemDTO toDTO(Item item){
    ItemDTO itemDTO = new ItemDTO();
    itemDTO.setId(item.getItemId());
    itemDTO.setName(item.getName());
    itemDTO.setDescription(item.getDescription());
    itemDTO.setPrice(item.getPrice());
    itemDTO.setImg(item.getImg());
    itemDTO.setCategory(itemCategoryConverter.toDTO(item.getItemCategory()));
    return itemDTO;
  }

  public Item toEntity(ItemDTO itemDTO,Item item){
    item.setName(itemDTO.getName());
    item.setDescription(itemDTO.getDescription());
    item.setPrice(itemDTO.getPrice());
    if (itemDTO.getImg() != null) {
      item.setImg(itemDTO.getImg());
    }
    return item;
  }

}