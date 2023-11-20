package com.FPTU.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetailItem> orderDetailItems = new ArrayList<>();

    private String name;

    private String img;

    private String description;

    private Long price;
}