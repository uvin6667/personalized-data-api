package com.isource.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Shopper {
    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopper_id", referencedColumnName = "shopper_id")
    private List<ShelfItem> shelf;
}
