package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    List<Purchase> findPurchaseByCustomer_Id(String id);
}
