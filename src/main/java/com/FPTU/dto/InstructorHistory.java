package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorHistory {
    private String nameBought;
    private String courseTitle;

    public InstructorHistory(Object[] values) {
        if (values.length >= 2) {
            this.courseTitle = values[0].toString();
            this.nameBought = values[1].toString();
        } else {
            // Handle the case where the Object[] does not contain enough elements
            throw new IllegalArgumentException("Invalid Object[] provided");
        }
    }
}
