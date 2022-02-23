package com.amigoscode.fraud.service;

import com.amigoscode.fraud.domain.FraudCheckHistory;
import com.amigoscode.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository repository;


    public Boolean isFraudulentCustomer(Integer customerId) {
        final var fraudCheckHistory = FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .build();
        repository.save(fraudCheckHistory);

        return false;
    }
}
