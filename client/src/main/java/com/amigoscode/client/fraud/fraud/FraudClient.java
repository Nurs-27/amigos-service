package com.amigoscode.client.fraud.fraud;

import com.amigoscode.client.fraud.fraud.model.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        url = "${clients.fraud.url}",
        name = "fraud"
)
public interface FraudClient {

    @GetMapping(path = "/api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
