package com.retail.retail_gateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client.user-service")
@Getter
@Setter
public class UserServiceProps {

    private String baseUrl;

    private String login;
}
