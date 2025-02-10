package com.langhavens.sdk.schwabapi;

import com.langhavens.sdk.schwabapi.model.*;
import com.langhavens.sdk.schwabapi.model.request.Index;
import com.langhavens.sdk.schwabapi.model.request.Market;
import com.langhavens.sdk.schwabapi.model.request.Projection;
import com.langhavens.sdk.schwabapi.model.request.Sort;
import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReactiveSchwabApiClient {

    Flux<InstrumentResponse> getInstruments(List<String> symbols, Projection projection);

    Flux<InstrumentResponse> getInstruments(String cusipId);

    Flux<MarketsResponse> getMarkets(@NonNull List<Market> markets);

    Flux<MarketsResponse> getMarkets(@NonNull List<Market> markets, Date date);

    Flux<MarketsResponse> getMarkets(@NonNull Market market);

    Flux<MarketsResponse> getMarkets(@NonNull Market market, Date date);

    Flux<MoversResponse> getMovers(@NonNull Index index, Sort sort, Integer frequency);

    Flux<MoversResponse> getMovers(@NonNull Index index, Sort sort);

    Flux<MoversResponse> getMovers(@NonNull Index index, Integer frequency);

    Flux<MoversResponse> getMovers(@NonNull Index index);

    Mono<PriceHistoryResponse> priceResponseHistory(String symbol);

    Flux<Map<String, QuoteResponse>> getQuotes(@NonNull String symbol, String fields);

}
