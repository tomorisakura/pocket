package com.enigma.pocket.util;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.repository.CustomerRepository;
import com.enigma.pocket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;

public class CommandRunner implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAddress("Jakarta");
        customer.setBirthDate(new Date());
        customer.setEmail("joh.doe@mail.com");
        customer.setPhone("089778990999");
    }
}
