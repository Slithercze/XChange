package org.knowm.xchange.okex.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class OkexDeposit {
    @JsonProperty("ccy")
    private String ccy;
    @JsonProperty("amt")
    private String amt;
    @JsonProperty("txId")
    private String txId;
    @JsonProperty("chain")
    private String chain;
    @JsonProperty("from")
    private String from;   // sender address
    @JsonProperty("to")
    private String to;     // your address
    @JsonProperty("state")
    private String state;  // e.g., success, pending
    @JsonProperty("ts")
    private String ts;     // ms
}
