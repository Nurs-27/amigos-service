package com.amigoscode.customer.service;

import com.amigoscode.client.fraud.fraud.FraudClient;
import com.amigoscode.client.fraud.notification.NotificationClient;
import com.amigoscode.client.fraud.notification.model.NotificationRequest;
import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.model.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
//    private final RestTemplate restTemplate;

//    private static final String FRAUD_CHECK_URL = "http://FRAUD/api/v1/fraud-check/{customerId}";

    @Transactional
    public void registerCustomer(CustomerRegistrationRequest request) {
        final var customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        repository.saveAndFlush(customer);

        final var fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (Objects.requireNonNull(fraudCheckResponse).isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...",
                                customer.getFirstName())
                )
        );
    }
}
