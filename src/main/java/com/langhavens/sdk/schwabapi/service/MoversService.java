package com.langhavens.sdk.schwabapi.service;

import com.langhavens.sdk.schwabapi.model.MoversResponse;
import com.langhavens.sdk.schwabapi.model.request.Index;
import com.langhavens.sdk.schwabapi.model.request.Sort;
import lombok.NonNull;
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
public class MoversService {

    public static final List<Integer> VALID_FREQUENCY_VALUES = List.of(0, 1, 5, 10, 30, 60);

    @Value("${schwab.api.movers-url}")
    private String moversUrl;

    private final WebClient schwabApiWebClient;

    Flux<MoversResponse> getMovers(@NonNull Index index, Sort sort, Integer frequency) {
        String uri = buildMoversUriWithParams(index, sort, frequency);

        return schwabApiWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(MoversResponse.class);
    }

    Flux<MoversResponse> getMovers(@NonNull Index index, Sort sort) {
        return this.getMovers(index, sort, null);
    }

    Flux<MoversResponse> getMovers(@NonNull Index index, Integer frequency) {
        return this.getMovers(index, null, frequency);
    }

    Flux<MoversResponse> getMovers(@NonNull Index index) {
        return this.getMovers(index, null, null);
    }

    private String buildMoversUriWithParams(Index index, Sort sort, Integer frequency) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(moversUrl)
                .path("/" + index);

        addSortAsQueryParamIfNotNull(sort, uriComponentsBuilder);
        addFrequencyAsQueryParamIfNotNull(frequency, uriComponentsBuilder);

        return uriComponentsBuilder.build()
                .toUriString();
    }

    private static void addSortAsQueryParamIfNotNull(Sort sort, UriComponentsBuilder uriComponentsBuilder) {
        if (sort != null) {
            uriComponentsBuilder.queryParam("sort", sort);
        }
    }

    private static void addFrequencyAsQueryParamIfNotNull(Integer frequency, UriComponentsBuilder uriComponentsBuilder) {
        if (frequency != null) {
            if (!VALID_FREQUENCY_VALUES.contains(frequency)) {
                throw new IllegalArgumentException("Explicit frequency argument provided but is not valid. Must be contained in the following list: " + VALID_FREQUENCY_VALUES);
            }

            uriComponentsBuilder.queryParam("frequency", frequency);
        }
    }

}
