package com.amigoscode.customer.service;

import com.amigoscode.amqp.RabbitMqMessageProducer;
import com.amigoscode.client.fraud.fraud.FraudClient;
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
    private final RabbitMqMessageProducer producer;
//    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public void registerCustomer(CustomerRegistrationRequest request) {
        final var customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        repository.saveAndFlush(customer);

        final var fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (Boolean.TRUE.equals(Objects.requireNonNull(fraudCheckResponse).isFraudster())) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName()));

        producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing"
        );
    }
}
