package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarketsResponse {

    Map<String, Market> forex;
    Map<String, Market> equity;
    Map<String, Market> future;
    Map<String, Market> option;
    Map<String, Market> bond;

}
