package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shelf_item")
@Data
@NoArgsConstructor
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

    public ShelfItem(Shopper shopper, Product product, Double relevancyScore) {
        this.shopper = shopper;
        this.product = product;
        this.relevancyScore = relevancyScore;
    }
}
