package com.enigma.pocket.services;

import com.enigma.pocket.entity.ProductHistoryPrice;

import java.util.List;

public interface ProductHistoryPriceServices {
    ProductHistoryPrice logPrice(ProductHistoryPrice productHistoryPrice);
    List<ProductHistoryPrice> findAllHistoryPrice();
    List<ProductHistoryPrice> findAllByProduct(String id);
}
