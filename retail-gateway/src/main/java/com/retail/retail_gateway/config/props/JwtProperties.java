package com.retail.retail_gateway.config.props;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app.jwt")
@Setter
@Getter
public class JwtProperties {
    private List<PublicPath> publicPaths;

    private String secret;

    private Integer expiration;
}
