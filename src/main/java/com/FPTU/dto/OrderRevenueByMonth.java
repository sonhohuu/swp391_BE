package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRevenueByMonth {
    private Long month;
    private Long year;
    private Long revenue;

    public OrderRevenueByMonth(Object[] values) {
        if (values.length >= 3) {
            this.month = Long.parseLong(values[0].toString());
            this.year = Long.parseLong(values[1].toString());
            this.revenue = Long.parseLong(values[2].toString());
        } else {
            // Handle the case where the Object[] does not contain enough elements
            throw new IllegalArgumentException("Invalid Object[] provided");
        }
    }
}
