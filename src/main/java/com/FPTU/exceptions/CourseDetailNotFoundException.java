package com.FPTU.exceptions;

public class CourseDetailNotFoundException extends RuntimeException{
    public CourseDetailNotFoundException(Long id) {
        super("Could not found the course's detail with id " + id);
    }
}
