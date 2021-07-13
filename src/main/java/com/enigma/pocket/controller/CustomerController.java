package com.enigma.pocket.controller;

import com.enigma.pocket.dto.CustomerSearchDto;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.services.CustomerServices;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.util.PageWrapper;
import com.enigma.pocket.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    ProductHistoryPriceServices productHistoryPriceServices;

    @GetMapping("/customers")
    public ResponseMessage<List<Customer>> getAllCustomer() {
        return ResponseMessage.success(200, customerServices.getAllCustomer());
    }

    @PostMapping("/customer")
    public void insertCustomer(@RequestBody Customer customer) {
        customerServices.createCustomer(customer);
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer) {
        customerServices.updateCustomer(customer);
    }

    @GetMapping("/customer")
    public PageWrapper<Customer> getAllCustomers(@RequestBody CustomerSearchDto customerSearchForm,
                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                       @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerServices.findCustomer(customerSearchForm, pageable);
        return new PageWrapper<>(200, customers);
    }

    @GetMapping("/customer/{id}/pocket")
    public ResponseMessage<Customer> findCustomerPocketById(@PathVariable(name = "id") String id) {
        Customer data = customerServices.getCustomerById(id);
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/customer/login")
    public ResponseMessage<Customer> findCredentialCustomer(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "password") String password) {
        Customer data = customerServices.login(username, password);
        return ResponseMessage.success(200, data);
    }

}
