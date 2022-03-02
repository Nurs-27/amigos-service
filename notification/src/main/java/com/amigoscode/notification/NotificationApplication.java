package com.amigoscode.notification;

import com.amigoscode.amqp.RabbitMqMessageProducer;
import com.amigoscode.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableFeignClients(basePackages = "com.amigoscode.client")
@SpringBootApplication(scanBasePackages = {"com.amigoscode.notification", "com.amigoscode.amqp"})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMqMessageProducer producer, NotificationConfig notificationConfig) {
        return args -> producer.publish(
                new Person("Nurs", 24),
                notificationConfig.getInternalExchange(),
                notificationConfig.getInternalNotificationRoutingKey()
        );
    }

    record Person(String name, Integer age) {

    }
}
