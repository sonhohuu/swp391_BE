package com.FPTU.service;


import com.FPTU.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
  List<ItemDTO> getAllItems();
  ItemDTO save(ItemDTO itemDTO);
  ItemDTO getItemById(Long id); // New method to retrieve an item by ID
  boolean deleteItemById(Long id); // New method to delete an item by ID
  List<ItemDTO> searchItems(String name); // New method to search for items

  boolean existsById(Long id);
  ItemDTO updateItemById(Long id, ItemDTO itemDTO); // New method to update an item by ID


}