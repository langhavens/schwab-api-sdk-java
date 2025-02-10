package com.langhavens.sdk.schwabapi.service;

import com.langhavens.sdk.schwabapi.model.MarketsResponse;
import com.langhavens.sdk.schwabapi.model.request.Market;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Profile("schwab")
@Slf4j
@Service
@RequiredArgsConstructor
public class MarketHoursService {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${schwab.api.markets-url}")
    private String marketsUrl;

    private final WebClient schwabApiWebClient;

    /**
     * Returns the {@link MarketsResponse} for the current day from the Schwab API.
     *
     * @param markets A {@link List} of {@link Market}
     * @return {@link Flux<MarketsResponse>}
     */
    Flux<MarketsResponse> getMarkets(@NonNull List<Market> markets) {
        return this.getMarkets(markets, null);
    }

    /**
     * Returns the {@link MarketsResponse} from the Schwab API.
     *
     * @param markets The {@link List} of {@link Market} to search for market data information on.
     * @param date The date of the desired market data.
     * @return {@link Flux<MarketsResponse>}
     */
    Flux<MarketsResponse> getMarkets(@NonNull List<Market> markets, Date date) {
        if (markets.isEmpty()) {
            throw new IllegalArgumentException("List of markets cannot be empty.");
        }

        String uri = buildUriWithMarketsQueryParams(markets, date);

        return schwabApiWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(MarketsResponse.class);
    }

    private String buildUriWithMarketsQueryParams(List<Market> markets, Date date) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(marketsUrl);

        markets.forEach(market -> {
            uriComponentsBuilder.queryParam("markets", market);
        });

        addDateAsQueryParamIfNotNull(date, uriComponentsBuilder);

        return uriComponentsBuilder.build()
                .toUriString();
    }

    /**
     * Returns the {@link MarketsResponse} for the current day from the Schwab API.
     *
     * @param market The {@link Market} to search for market data information on.
     * @return {@link Flux<MarketsResponse>}
     */
    Flux<MarketsResponse> getMarkets(@NonNull Market market) {
        return this.getMarkets(market, null);
    }

    /**
     * Returns the {@link MarketsResponse} from the Schwab API.
     *
     * @param market The {@link Market} to search for market data information on.
     * @param date The date of the desired market data.
     * @return {@link Flux<MarketsResponse>}
     */
    Flux<MarketsResponse> getMarkets(@NonNull Market market, Date date) {
        String uri = buildUriWithMarketPathParam(market, date);

        return schwabApiWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(MarketsResponse.class);
    }

    private String buildUriWithMarketPathParam(Market market, Date date) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(marketsUrl)
                .path("/" + market);

        addDateAsQueryParamIfNotNull(date, uriComponentsBuilder);

        return uriComponentsBuilder.build()
                .toUriString();
    }

    private static void addDateAsQueryParamIfNotNull(Date date, UriComponentsBuilder uriComponentsBuilder) {
        if (date != null) {
            String dateAsString = DATE_FORMAT.format(date);
            uriComponentsBuilder.queryParam("date", dateAsString);
        }
    }

}
