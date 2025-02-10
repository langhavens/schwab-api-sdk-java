package com.langhavens.sdk.schwabapi.service;

import com.langhavens.sdk.schwabapi.model.InstrumentResponse;
import com.langhavens.sdk.schwabapi.model.request.Projection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.List;

@Profile("schwab")
@Slf4j
@Service
@RequiredArgsConstructor
public class InstrumentsService {

    @Value("${schwab.api.instruments-url}")
    private String instrumentsUrl;

    private final WebClient schwabApiWebClient;

    public Flux<InstrumentResponse> getInstruments(List<String> symbols, Projection projection) {
        return schwabApiWebClient.get()
                .uri(UriComponentsBuilder.fromUriString(instrumentsUrl)
                        .queryParam("symbol", String.join(",", symbols))
                        .queryParam("projection", projection)
                        .build()
                        .toUriString()
                )
                .retrieve()
                .bodyToFlux(InstrumentResponse.class);
    }

    public Flux<InstrumentResponse> getInstruments(String cusipId) {
        return schwabApiWebClient.get()
                .uri(UriComponentsBuilder.fromUriString(instrumentsUrl)
                        .pathSegment("{cusipId}")
                        .build(cusipId)
                )
                .retrieve()
                .bodyToFlux(InstrumentResponse.class);
    }

}
