package com.amigoscode.client.fraud.notification;

import com.amigoscode.client.fraud.notification.model.NotificationRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        url = "${clients.fraud.url}",
        value = "notification"
)
public interface NotificationClient {

    @PostMapping("/api/v1/notifications")
    void sendNotification(@RequestBody NotificationRequest request);
}
