package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseDiscountDTO {
    private Long id;
    private Long courseId;
    private String name;
    private Long percent;
    private Date startDate;
    private Date endDate;
}