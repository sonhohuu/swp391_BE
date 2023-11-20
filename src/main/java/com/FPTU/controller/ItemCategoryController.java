package com.FPTU.controller;

import com.FPTU.dto.ItemCategoryDTO;
import com.FPTU.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemcategory")
@CrossOrigin("http://127.0.0.1:5173/")
public class ItemCategoryController {
  @Autowired
  private ItemCategoryService itemCategoryService;
  @GetMapping
  public List<ItemCategoryDTO> getAllItemCategory(){
    return itemCategoryService.getAllItemCategory();
  }
}