package com.enigma.pocket.services.impl;

import com.enigma.pocket.dto.ProductDto;
import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.repository.ProductRepository;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.services.ProductServices;
import com.enigma.pocket.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductServices {

    String messageFormat = "Product id:%s not found";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductHistoryPriceServices productHistoryPriceServices;

    @Override
    public Product findProductById(String id) {
        validateProduct(id);
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findProduct(ProductDto productDto, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.findProduct(productDto), pageable);
    }

    @Override
    public Product insertProduct(Product product) {
        return persistProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        validateProduct(product.getId());
        return persistProduct(product);
    }

    private void validateProduct(String id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(messageFormat, id));
        }
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductByName(String productName) {
        return productRepository.findProductByProductName(productName);
    }

    private Product persistProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        ProductHistoryPrice initialHistory = new ProductHistoryPrice(savedProduct);
        productHistoryPriceServices.logPrice(initialHistory);
        return savedProduct;
    }
}
