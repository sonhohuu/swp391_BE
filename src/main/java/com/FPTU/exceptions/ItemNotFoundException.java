package com.FPTU.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id) {
        super("Could not found the item with id " + id);
    }
}
