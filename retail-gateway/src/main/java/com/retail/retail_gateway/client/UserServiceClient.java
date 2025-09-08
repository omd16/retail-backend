package com.retail.retail_gateway.client;

import com.retail.retail_gateway.config.UserServiceProps;
import com.retail.retail_gateway.dto.LoginRequest;
import com.retail.retail_gateway.dto.LoginResponse;
import com.retail.retail_gateway.exception.LoginException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class UserServiceClient {

    private final UserServiceProps userServiceProps;
    private final WebClient webClient;

    public UserServiceClient(UserServiceProps userServiceProps, WebClient.Builder webClientbuilder) {
        this.userServiceProps = userServiceProps;
        this.webClient = webClientbuilder.build();
    }

    public Mono<LoginResponse> loginRequest(LoginRequest request){
        var url = String.format("%s%s", userServiceProps.getBaseUrl(), userServiceProps.getLogin());
        var res = webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request).retrieve().bodyToMono(LoginResponse.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if(ex.getStatusCode().is4xxClientError()){
                        return Mono.error(new LoginException("Invalid username or password", ex.getStatusCode().value()));
                    }else{
                        return Mono.error(new LoginException("Error occurred while login", ex.getStatusCode().value()));
                    }
                });
        return res;
    }
}
