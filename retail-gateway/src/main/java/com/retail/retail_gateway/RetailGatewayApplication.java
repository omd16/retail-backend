package com.retail.retail_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class RetailGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(RetailGatewayApplication.class, args);
  }
}
