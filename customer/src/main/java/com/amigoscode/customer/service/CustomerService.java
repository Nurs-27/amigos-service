package com.amigoscode.customer.service;

import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.model.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        repository.save(customer);
    }
}
