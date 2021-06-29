package com.enigma.pocket.services.impl;

import com.enigma.pocket.constant.AppConfigConstant;
import com.enigma.pocket.entity.Customer;
import com.enigma.pocket.services.AppConfigService;
import com.enigma.pocket.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    RestTemplate restTemplate;

    //http://localhost:8090/api/v1/debet

    @Autowired
    AppConfigService appConfigService;

    @Override
    public void sendWallet(Customer customer, BigDecimal total) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(appConfigService.getValue(AppConfigConstant.OPO_WALLET_ENDPOINT_URL))
                .queryParam("phone", customer.getPhone())
                .queryParam("amount", total);
        System.out.println(componentsBuilder.toUriString());
        restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);
    }
}
