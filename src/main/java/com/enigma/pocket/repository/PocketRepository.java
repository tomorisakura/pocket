package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PocketRepository extends JpaRepository<Pocket, String> {
    List<Pocket> findPocketByProductAndCustomer(Product product, Customer customer);
}
