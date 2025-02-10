package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class PriceHistoryResponse {

    private String symbol;
    private String empty;
    private String previousClose;
    private String previousCloseDate;
    private List<Candle> candles;

    @Data
    public static class Candle {

        private String open;
        private String high;
        private String low;
        private String close;
        private String volume;
        private String datetime;

    }

}
