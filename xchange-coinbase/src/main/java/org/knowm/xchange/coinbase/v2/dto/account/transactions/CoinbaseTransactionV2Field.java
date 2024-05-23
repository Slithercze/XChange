package org.knowm.xchange.coinbase.v2.dto.account.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CoinbaseTransactionV2Field {

  protected String id;
  protected String resource;
  protected String paymentMethodName;
  protected String resourcePath;

  public CoinbaseTransactionV2Field(
      @JsonProperty("id") String id,
      @JsonProperty("resource") String resource,
      @JsonProperty("payment_method_name") String paymentMethodName,
      @JsonProperty("resource_path") String resourcePath) {
    this.id = id;
    this.resource = resource;
    this.resourcePath = resourcePath;
    this.paymentMethodName = paymentMethodName;
  }

  @Override
  public String toString() {
    return "{\"CoinbaseTransactionV2Field\":{"
            + "\"id\":\"" + id + "\""
            + ", \"resource\":\"" + resource + "\""
            + ", \"paymentMethodName\":\"" + paymentMethodName + "\""
            + ", \"resourcePath\":\"" + resourcePath + "\""
            + "}}";
  }
}
