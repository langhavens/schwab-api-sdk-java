package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteResponse {

    String assetMainType;
    String assetSubType;
    String quoteType;
    Boolean realtime;
    String ssid;
    String symbol;
    ExtendedMarket extended;
    Fundamental fundamental;
    QuoteEquity quote;
    ReferenceEquity reference;
    RegularMarket regular;

    @Value
    @Builder
    @Jacksonized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExtendedMarket {
        BigDecimal askPrice;
        BigInteger askSize;
        BigDecimal bidPrice;
        BigInteger bidSize;
        BigDecimal lastPrice;
        BigInteger lastSize;
        BigDecimal mark;
        BigInteger quoteTime;
        BigInteger totalVolume;
        BigInteger tradeTime;
    }

    @Value
    @Builder
    @Jacksonized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Fundamental {

        BigDecimal avg10DaysVolume;
        BigDecimal avg1YearVolume;
        Date declarationDate;
        BigDecimal divAmount;
        Date divExDate;
        BigInteger divFreq;
        BigDecimal divPayAmount;
        Date divPayDate;
        BigDecimal divYield;
        BigDecimal eps;
        BigDecimal fundLeverageFactor;
        FundStrategy fundStrategy;
        Date nextDivExDate;
        Date nextDivPayDate;
        Date lastEarningsDate;
        BigDecimal peRatio;

        public enum FundStrategy {
            A("Active"), L("Leveraged"), P("Passive"), Q("Quantitative"), S("Short");

            private final String meaning;

            FundStrategy(String meaning) {
                this.meaning = meaning;
            }

            String getMeaning() {
                return this.meaning;
            }
        }

    }

    @Value
    @Builder
    @Jacksonized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class QuoteEquity {

        @JsonProperty("52WeekHigh")
        BigDecimal _52WeekHigh;
        @JsonProperty("52WeekLow")
        BigDecimal _52WeekLow;
        String askMICId;
        BigDecimal askPrice;
        BigInteger askSize;
        BigInteger askTime;
        String bidMICId;
        BigDecimal bidPrice;
        BigInteger bidSize;
        BigInteger bidTime;
        BigDecimal closePrice;
        BigDecimal highPrice;
        String lastMICId;
        BigDecimal lastPrice;
        BigInteger lastSize;
        BigDecimal lowPrice;
        BigDecimal mark;
        BigDecimal markChange;
        BigDecimal markPercentChange;
        BigDecimal netChange;
        BigDecimal netPercentChange;
        BigDecimal openPrice;
        BigDecimal postMarketChange;
        BigDecimal postMarketPercentChange;
        BigInteger quoteTime;
        String securityStatus;
        BigInteger totalVolume;
        BigInteger tradeTime;
        BigDecimal volatility;

    }

    @Value
    @Builder
    @Jacksonized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReferenceEquity {

        String cusip;
        String description;
        String exchange;
        String exchangeName;
        String fsiDesc;
        BigInteger htbQuantity;
        BigDecimal htbRate;
        Boolean isHardToBorrow;
        Boolean isShortable;
        String otcMarketTier;

    }

    @Value
    @Builder
    @Jacksonized
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RegularMarket {

        BigDecimal regularMarketLastPrice;
        BigInteger regularMarketLastSize;
        BigDecimal regularMarketNetChange;
        BigDecimal regularMarketPercentChange;
        BigInteger regularMarketTradeTime;

    }

}
