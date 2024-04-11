package com.isource.personalizeddataapi.model;

import java.util.List;

public record PersonalisedProductList (String shopperId, List<PersonalisedProductDetail> shelf){

}
