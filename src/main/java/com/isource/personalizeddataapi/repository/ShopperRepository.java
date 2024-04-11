package com.isource.personalizeddataapi.repository;

import com.isource.personalizeddataapi.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopperRepository extends JpaRepository<Shopper,String> {

    Shopper findByShopperId(String shopperId);

}
