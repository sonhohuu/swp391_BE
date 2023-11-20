package com.FPTU.service.impl;

import com.FPTU.converter.ItemConverter;
import com.FPTU.dto.ItemDTO;
import com.FPTU.model.Item;
import com.FPTU.model.ItemCategory;
import com.FPTU.repository.ItemCategoryRepository;
import com.FPTU.repository.ItemRepository;
import com.FPTU.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemConverter itemConverter;
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> list = new ArrayList<>();
        for (Item i : itemRepository.findAll()) {
            list.add(itemConverter.toDTO(i));
        }
        return list;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Item item = new Item();
        if (itemDTO.getId() != null) {
            Item oldItem = itemRepository.getOne(itemDTO.getId());
            item = itemConverter.toEntity(itemDTO, oldItem);
        } else {
            item = itemConverter.toEntity(itemDTO);
        }
        ItemCategory itemCategory = itemCategoryRepository.getOne(itemDTO.getCategory().getId());
        item.setItemCategory(itemCategory);
        item = itemRepository.save(item);
        return itemConverter.toDTO(item);
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        return itemOptional.map(itemConverter::toDTO).orElse(null);
    }

    @Override
    public boolean deleteItemById(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemDTO> searchItems(String name) {
        List<Item> matchingItems = itemRepository.findByNameContainingIgnoreCase(name);
        return matchingItems.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public ItemDTO updateItemById(Long id, ItemDTO itemDTO) {
        Optional<Item> existingItemOptional = itemRepository.findById(id);
        if (!existingItemOptional.isPresent()) {
            return null; // Item with the given ID not found
        }

        Item existingItem = existingItemOptional.get();
        existingItem = itemConverter.toEntity(itemDTO, existingItem);
        existingItem = itemRepository.save(existingItem);

        return itemConverter.toDTO(existingItem);
    }
}
