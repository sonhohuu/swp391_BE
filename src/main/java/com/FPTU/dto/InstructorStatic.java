package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorStatic {
    private Long course_id;
    private Long totalStudent;
    private Long totalRevenue;

    public InstructorStatic(Object[] values) {
        if (values.length >= 3) {
            this.course_id = Long.parseLong(values[0].toString());
            this.totalRevenue = Long.parseLong(values[2].toString()) * Long.parseLong(values[1].toString());
            this.totalStudent = Long.parseLong(values[2].toString());
        } else {
            // Handle the case where the Object[] does not contain enough elements
            throw new IllegalArgumentException("Invalid Object[] provided");
        }
    }
}
