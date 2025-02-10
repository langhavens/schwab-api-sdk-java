package com.langhavens.sdk.schwabapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuotesResponse extends ParameterizedTypeReference<Map<String, QuoteResponse>> {



}
