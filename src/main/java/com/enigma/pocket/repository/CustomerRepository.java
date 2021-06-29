package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> , JpaSpecificationExecutor<Customer> {
    List<Customer> findAllByFirstNameStartingWithAndEmailContaining(String firstName, String email, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.status=1") //native query with jpql
    List<Customer> findActiveCustomer();

    Customer findCustomerByUsernameAndPassword(String username, String password);
}
