package com.enigma.pocket.services.impl;

import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.repository.ProductHistoryPriceRepository;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryPriceServices {

    @Autowired
    ProductHistoryPriceRepository productHistoryPriceRepository;

    @Autowired
    ProductServices productServices;

    @Override
    public ProductHistoryPrice logPrice(ProductHistoryPrice productHistoryPrice) {
        return productHistoryPriceRepository.save(productHistoryPrice);
    }

    @Override
    public List<ProductHistoryPrice> findAllHistoryPrice() {
        return productHistoryPriceRepository.findAll();
    }

    @Override
    public List<ProductHistoryPrice> findAllByProduct(String id) {
        Product product = productServices.findProductById(id);
        return product.getHistoryPrices();
    }
}
