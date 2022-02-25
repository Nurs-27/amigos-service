package com.amigoscode.client.fraud.notification.model;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
