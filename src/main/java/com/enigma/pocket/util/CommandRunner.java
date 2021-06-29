package com.enigma.pocket.util;

import com.enigma.pocket.dto.Car;
import com.enigma.pocket.dto.Engine;
import com.enigma.pocket.dto.EngineX;
import com.enigma.pocket.dto.Prices;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Product;
import com.enigma.pocket.repository.CustomerRepository;
import com.enigma.pocket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandRunner implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Engine engine = new Engine("V8", "9000");
        Car car = new Car("nissan", "white", engine);

        //with lambda
        car.printSuperEngineSound((sound)-> {
            System.out.println(sound);
        });

        //without lambda
        car.printSuperEngineSound(new EngineX() {
            @Override
            public void supperSound(String sound) {
                System.out.println(sound);
            }
        });

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        set.stream().forEach(result -> {
            System.out.println(result * 2);
        });

        List<Customer> customers = customerRepository.findActiveCustomer();

        customers.stream().forEach(customer -> System.out.println(customers));

        List<Product> products = productRepository.findAllProductGreaterThan(new BigDecimal(100000), 1);
        products.forEach(product -> System.out.println(product));

        List<Prices> prices = productRepository.prices();

        prices.stream().forEach(price -> System.out.println(price.getProductPriceBuy()+" || "+price.getProductPriceSell()));
    }
}
