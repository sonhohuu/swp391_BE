package com.FPTU.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long id) {
        super("Could not found the course with id " + id);
    }
}