package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;
    private String category;
    private String brand;
}
