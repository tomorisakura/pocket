package com.enigma.pocket.services.impl;

import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.entity.Product;
import com.enigma.pocket.repository.PocketRepository;
import com.enigma.pocket.services.CustomerServices;
import com.enigma.pocket.services.PocketService;
import com.enigma.pocket.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PocketServiceImpl implements PocketService {

    @Autowired
    PocketRepository pocketRepository;

    @Autowired
    CustomerServices customerServices;

    @Autowired
    ProductServices productServices;

    @Override
    public List<Pocket> getAllPocket() {
        return pocketRepository.findAll();
    }

    @Override
    public Pocket getPocketById(String id) {
        return pocketRepository.findById(id).get();
    }

    @Override
    public Pocket createPocket(Pocket pocket) {
        Customer customer = customerServices.getCustomerById(pocket.getCustomerId());
        Product product = productServices.findProductById(pocket.getProductId());
        pocket.setPocketQty(0.0);
        pocket.setCustomer(customer);
        pocket.setProduct(product);
        pocket.setTotalPrice(BigDecimal.ZERO);
        return pocketRepository.save(pocket);
    }

    @Override
    public Pocket updatePocket(Pocket pocket) {
        Customer customer = customerServices.getCustomerById(pocket.getCustomerId());
        Product product = productServices.findProductById(pocket.getProductId());
        pocket.setCustomer(customer);
        pocket.setProduct(product);
        pocket.setPocketQty(pocket.getPocketQty());
        return pocketRepository.save(pocket);
    }

    @Override
    public void topUp(Pocket pocket, Double qty, BigDecimal total) {
        pocket.setPocketQty(pocket.getPocketQty()+qty);
        pocket.setTotalPrice(pocket.getTotalPrice().add(total));
        pocketRepository.save(pocket);
    }

    @Override
    public void sellPocket(Pocket pocket, Double qty, BigDecimal total) {
        pocket.setPocketQty(pocket.getPocketQty() - qty);
        pocket.setTotalPrice(pocket.getTotalPrice().subtract(total));
        pocketRepository.save(pocket);
    }

    @Override
    public void deletePocket(String pocketId) {
        pocketRepository.deleteById(pocketId);
    }

    @Override
    public List<Pocket> findPocketByCustomerAndProduct(String customerId, String productId) {
        Customer customer = customerServices.findCustomerById(customerId);
        Product product = productServices.findProductById(productId);
        return pocketRepository.findPocketByProductAndCustomer(product, customer);
    }
}
