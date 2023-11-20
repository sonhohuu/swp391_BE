package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private Long id;

  @NotNull(message = "Name null")
  private String name;

  @NotNull(message = "Description null")
  private String description;

  @NotNull(message = "Price null")
  private Long price;

  @NotNull(message = "Category null")
  private ItemCategoryDTO category;

  private String img;
}