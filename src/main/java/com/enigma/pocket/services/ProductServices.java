package com.enigma.pocket.services;

import com.enigma.pocket.dto.ProductDto;
import com.enigma.pocket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServices {
    Product findProductById(String id);
    List<Product> findAllProduct();
    Page<Product> findProduct(ProductDto productDto, Pageable pageable);
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductById(String id);
    Product findProductByName(String productName);
}
