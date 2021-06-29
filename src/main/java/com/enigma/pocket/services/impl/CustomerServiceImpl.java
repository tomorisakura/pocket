package com.enigma.pocket.services.impl;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.repository.CustomerRepository;
import com.enigma.pocket.services.CustomerServices;
import com.enigma.pocket.spec.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServices {

    private final String message = "customer with id:%s not found";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String id) {
        validatePresent(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        validatePresent(customer.getId());
        customerRepository.save(customer);
    }

    private void validatePresent(String id) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(message, id));
        }
    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    @Override
    public Page<Customer> findCustomer(CustomerSearchDto customerSearchFrom, Pageable pageable) {
        return customerRepository.findAll(CustomerSpecification.findCustomers(customerSearchFrom), pageable);
    }

    @Override
    public List<Customer> findCustomerByFirstNameOrEmail(String firstName, String email, Pageable pageable) {
        return customerRepository.findAllByFirstNameStartingWithAndEmailContaining(firstName, email, pageable);
    }

    @Override
    public Customer findCustomerByPocket(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer login(String username, String password) {
        return customerRepository.findCustomerByUsernameAndPassword(username, password);
    }
}
