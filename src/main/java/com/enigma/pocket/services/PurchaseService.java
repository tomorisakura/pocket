package com.enigma.pocket.services;

import com.enigma.pocket.entity.Purchase;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PurchaseService {
    Purchase purchase(Purchase purchase, String customerId) throws JsonProcessingException;
    Purchase findPurchaseById(String id);
    List<Purchase> findAllPurchase();
    List<Purchase> findPurchaseByCustomerId(String id);
}
