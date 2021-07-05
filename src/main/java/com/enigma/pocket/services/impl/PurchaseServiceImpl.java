package com.enigma.pocket.services.impl;

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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);
        switch (purchase.getPurchaseType()) {
            case "BUY":
                for (PurchaseDetail result: purchase.getPurchaseDetails()) {
                    Pocket pocket = getPocketFromRemote(result, purchase);
                    total.set(BigDecimal.valueOf(result.getPrice() * result.getQuantityInGram()));
                    pocketService.topUp(pocket, result.getQuantityInGram(), total.get());
                }
                break;
            case "SELL":
                purchase.getPurchaseDetails().forEach(result-> {
                    Pocket pocket = getPocketFromRemote(result, purchase);
                    total.set(BigDecimal.valueOf(result.getPrice() * result.getQuantityInGram()));
                    pocketService.sellPocket(pocket, result.getQuantityInGram(), total.get());
                });
                break;
        }
//        walletService.sendWallet(customer, total);
//        PurchaseDto purchaseDto = new PurchaseDto();
//        purchaseDto.setEmailTo(purchase.getCustomer().getEmail());
//        purchaseDto.setCustomerName(purchase.getCustomer().getFirstName() +" "+purchase.getCustomer().getLastName());
//        purchaseDto.setTotal(total);
//        String jsonPurchase = objectMapper.writeValueAsString(purchaseDto);
//        kafkaTemplate.send("simple-notification",jsonPurchase);
        return purchaseRepository.save(purchase);
    }

    private Pocket getPocketFromRemote(PurchaseDetail purchaseDetail, Purchase purchase) {
        Pocket pocket = pocketService.getPocketById(purchaseDetail.getPocket().getId());
        purchaseDetail.setProduct(pocket.getProduct());
        purchaseDetail.setPrice(pocket.getProduct().getProductPriceSell());
        purchaseDetail.setPurchase(purchase);
        return pocket;
    }

    @Override
    public Purchase findPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> findPurchaseByCustomerId(String id) {
        return purchaseRepository.findPurchaseByCustomer_Id(id);
    }
}
