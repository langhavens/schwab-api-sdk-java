package com.langhavens.sdk.schwabapi.service;

import com.langhavens.sdk.schwabapi.model.PriceHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceHistoryService {

    private final WebClient schwabWebClient;

    public Mono<PriceHistoryResponse> priceResponseHistory(String symbol) {
        return schwabWebClient.get()
                .uri(String.format("https://api.schwabapi.com/marketdata/v1/pricehistory?symbol=%s&needPreviousClose=true", symbol))
//                .uri("https://api.schwabapi.com/marketdata/v1/quotes?symbols=GSL&indicative=false")
                .attributes(
                        ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("schwab")
                ).retrieve()
                .bodyToMono(PriceHistoryResponse.class);
    }

}
