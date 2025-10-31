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
public class OkexWithdrawal {
    @JsonProperty("ccy")   private String ccy;
    @JsonProperty("amt")   private String amt;
    @JsonProperty("fee")   private String fee;
    @JsonProperty("feeCcy")   private String feeCcy;
    @JsonProperty("txId")  private String txId;
    @JsonProperty("chain") private String chain;
    @JsonProperty("toAddr")private String toAddr;
    @JsonProperty("state") private String state;  // e.g., success, pending, canceled
    @JsonProperty("ts")    private String ts;     // ms
}