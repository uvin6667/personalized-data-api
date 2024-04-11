package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shelf_item")
@Data
public class ShelfItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "relevancy_score")
    private Double relevancyScore;
}