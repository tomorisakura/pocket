package com.enigma.pocket.controller;


import com.enigma.pocket.dto.ProductDto;
import com.enigma.pocket.entity.Product;
import com.enigma.pocket.entity.ProductHistoryPrice;
import com.enigma.pocket.services.ProductHistoryPriceServices;
import com.enigma.pocket.services.ProductServices;
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
public class ProductController {

    @Autowired
    ProductServices productServices;

    @Autowired
    ProductHistoryPriceServices productHistoryPriceServices;

    @GetMapping("/products")
    public ResponseMessage<List<Product>> getAllProduct() {
        List<Product> data = productServices.findAllProduct();
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/product")
    public PageWrapper<Product> getAllProduct(@RequestBody ProductDto productDto,
                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productServices.findProduct(productDto, pageable);
        return new PageWrapper<>(200, products);
    }

    @GetMapping("/product/{id}")
    public ResponseMessage<Product> getProductById(@PathVariable(name = "id") String id) {
        Product data = productServices.findProductById(id);
        return ResponseMessage.success(200, data);
    }

    @PostMapping("/product")
    public ResponseMessage<Product> insertProduct(@RequestBody Product product) {
        Product data = productServices.insertProduct(product);
        return ResponseMessage.success(200, data);
    }

    @PutMapping("/product")
    public ResponseMessage<Product> updateProductById(@RequestBody Product product) {
        Product data = productServices.updateProduct(product);
        return ResponseMessage.success(200, data);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productServices.deleteProductById(id);
    }

    @GetMapping("/product/{id}/history")
    public ResponseMessage<List<ProductHistoryPrice>> getHistoryByProduct(@PathVariable(name = "id") String id) {
        List<ProductHistoryPrice> data = productHistoryPriceServices.findAllByProduct(id);
        return ResponseMessage.success(200, data);
    }

    @GetMapping("/product/name/{productName}")
    public ResponseMessage<Product> getProductByName(@PathVariable(name = "productName") String productName) {
        Product data = productServices.findProductByName(productName);
        return ResponseMessage.success(200, data);
    }

}
