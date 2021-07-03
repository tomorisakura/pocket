package com.enigma.pocket.services.impl;

import com.enigma.pocket.dto.PurchaseDto;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.entity.Purchase;
import com.enigma.pocket.entity.PurchaseDetail;
import com.enigma.pocket.repository.PurchaseRepository;
import com.enigma.pocket.services.CustomerServices;
import com.enigma.pocket.services.PocketService;
import com.enigma.pocket.services.PurchaseService;
import com.enigma.pocket.services.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerServices customerServices;

    @Autowired
    PocketService pocketService;

    @Autowired
    WalletService walletService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Override
    public Purchase purchase(Purchase purchase, String customerId) throws JsonProcessingException {
        Customer customer = customerServices.getCustomerById(customerId);
        purchase.setCustomer(customer);
        purchase.setPurchaseDate(LocalDateTime.now());
        BigDecimal total = BigDecimal.ZERO;
        if (purchase.getPurchaseType() == 0) {
            for (PurchaseDetail result: purchase.getPurchaseDetails()) {
                Pocket pocket = pocketService.getPocketById(result.getPocket().getId());
                pocketService.topUp(pocket, result.getQuantityInGram());
                result.setProduct(pocket.getProduct());
                result.setPrice(pocket.getProduct().getProductPriceSell());
                result.setPurchase(purchase);
                total = total.add(BigDecimal.valueOf(result.getPrice())).multiply(BigDecimal.valueOf(result.getQuantityInGram()));
            }
        } else if(purchase.getPurchaseType() == 1) {
            purchase.getPurchaseDetails().forEach(result-> {
                Pocket pocket = pocketService.getPocketById(result.getPocket().getId());
                pocketService.sellPocket(pocket, pocket.getPocketQty());
                result.setProduct(pocket.getProduct());
                result.setPrice(pocket.getProduct().getProductPriceSell());
                result.setPurchase(purchase);
            });
        }

//        walletService.sendWallet(customer, total);
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setEmailTo(purchase.getCustomer().getEmail());
        purchaseDto.setCustomerName(purchase.getCustomer().getFirstName() +" "+purchase.getCustomer().getLastName());
        purchaseDto.setTotal(total);
//        String jsonPurchase = objectMapper.writeValueAsString(purchaseDto);
//        kafkaTemplate.send("simple-notification",jsonPurchase);
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }
}
