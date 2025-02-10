package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Instrument {

    FundamentalInst fundamental;
    String cusip;
    String symbol;
    String description;
    String exchange;
    AssetType assetType;
    AssetType type;

}
