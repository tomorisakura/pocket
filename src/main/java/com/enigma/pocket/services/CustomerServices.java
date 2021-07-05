package com.enigma.pocket.services;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerServices{
    public Customer getCustomerById(String id);
    public List<Customer> getAllCustomer();
    public void createCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Integer id);
    public Page<Customer> findCustomer(CustomerSearchDto customerSearchFrom, Pageable pageable);
    public List<Customer> findCustomerByFirstNameOrEmail(String firstName, String email, Pageable pageable);
    Customer findCustomerByPocket(String id);
    Customer login(String username, String password);
    Customer findCustomerById(String customerId);
}
