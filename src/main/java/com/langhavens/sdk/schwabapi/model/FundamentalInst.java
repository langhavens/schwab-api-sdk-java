package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundamentalInst {

    String symbol;
    String high52;
    String low52;
    String dividendAmount;
    String dividendYield;
    String dividendDate;
    String peRatio;
    String pegRatio;
    String pbRatio;
    String prRatio;
    String pcfRatio;
    String grossMarginTTM;
    String grossMarginMRQ;
    String netProfitMarginTTM;
    String netProfitMarginMRQ;
    String operatingMarginTTM;
    String operatingMarginMRQ;
    String returnOnEquity;
    String returnOnAssets;
    String returnOnInvestment;
    String quickRatio;
    String currentRatio;
    String interestCoverage;
    String totalDebtToCapital;
    String ltDebtToEquity;
    String totalDebtToEquity;
    String epsTTM;
    String epsChangePercentTTM;
    String epsChangeYear;
    String epsChange;
    String revChangeYear;
    String revChangeTTM;
    String revChangeIn;
    String sharesOutstanding;
    String marketCapFloat;
    String marketCap;
    String bookValuePerShare;
    String shortIntToFloat;
    String shortIntDayToCover;
    String divGrowthRate3Year;
    String dividendPayAmount;
    String dividendPayDate;
    String beta;
    String vol1DayAvg;
    String vol10DayAvg;
    String vol3MonthAvg;
    String avg10DaysVolume;
    String avg1DayVolume;
    String avg3MonthVolume;
    String declarationDate;
    String dividendFreq;
    String eps;
    String corpactionDate;
    String dtnVolume;
    String nextDividendPayDate;
    String nextDividendDate;
    String fundLeverageFactor;
    String fundStrategy;

}
