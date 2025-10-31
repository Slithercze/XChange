package org.knowm.xchange.okex.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkexAccountInstruments {
    @JsonProperty("instId")
    private String instId;
    @JsonProperty("baseCcy")
    private String baseCcy;     // spot/margin only
    @JsonProperty("quoteCcy")
    private String quoteCcy;    // spot/margin only
}