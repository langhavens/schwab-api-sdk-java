package com.langhavens.sdk.schwabapi.service;

import com.langhavens.sdk.schwabapi.model.QuoteResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.Map;

@Profile("schwab")
@Slf4j
@Service
@RequiredArgsConstructor
public class QuotesService {

    @Value("${schwab.api.market-data-url}")
    private String marketDataUrl;
    @Value("${schwab.api.quotes-url}")
    private String quotesUrl;

    private final WebClient schwabApiWebClient;

    Flux<Map<String, QuoteResponse>> getQuotes(@NonNull String symbol, String fields) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(marketDataUrl);
        uriComponentsBuilder.path("/" + symbol);
        uriComponentsBuilder.path("/quotes");

        var uri = uriComponentsBuilder.build()
                .toUriString();

        return schwabApiWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, QuoteResponse>>() {});
    }

    @Bean
    public String test() {
        this.getQuotes("TSLA", null).doOnNext(System.err::println).subscribe();
        return "";
    }

}
