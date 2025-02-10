package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Market {

    String date;
    MarketType marketType;
    String exchange;
    String category;
    String product;
    String productName;
    Boolean isOpen;
    SessionHours sessionHours;


}
