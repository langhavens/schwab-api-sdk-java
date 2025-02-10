package com.langhavens.sdk.schwabapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("schwab")
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SchwabApiWebClientConfiguration {

    @Bean(name = "schwabApiWebClient")
    public WebClient schwabApiWebClient(ReactiveClientRegistrationRepository clientRegistrations, ExchangeStrategies exchangeStrategies) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction serverOAuth2AuthorizedClientExchangeFilterFunction = getServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations);

        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .filter(serverOAuth2AuthorizedClientExchangeFilterFunction)
                .build();
    }

    private static ServerOAuth2AuthorizedClientExchangeFilterFunction getServerOAuth2AuthorizedClientExchangeFilterFunction(ReactiveClientRegistrationRepository clientRegistrations) {
        InMemoryReactiveOAuth2AuthorizedClientService inMemoryReactiveOAuth2AuthorizedClientService = new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations);
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientServiceReactiveOAuth2AuthorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations, inMemoryReactiveOAuth2AuthorizedClientService);
        ServerOAuth2AuthorizedClientExchangeFilterFunction serverOAuth2AuthorizedClientExchangeFilterFunction = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientServiceReactiveOAuth2AuthorizedClientManager);
        serverOAuth2AuthorizedClientExchangeFilterFunction.setDefaultClientRegistrationId("schwab");
        return serverOAuth2AuthorizedClientExchangeFilterFunction;
    }

    @Bean
    public ExchangeStrategies exchangeStrategies() {
        // Increases the max size of responses because default gets overloaded by large responses
        final int size = 16 * 1024 * 1024;
        return ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
    }

}
