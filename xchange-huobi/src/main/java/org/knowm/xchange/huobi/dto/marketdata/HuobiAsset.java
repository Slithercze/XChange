package org.knowm.xchange.huobi.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiAsset {

  private final String asset;

  public HuobiAsset(@JsonProperty("cc") String asset) {
    this.asset = asset;
  }

  public String getAsset() {
    return asset;
  }

  @Override
  public String toString() {
    return String.format("HuobiAsset [%s]", asset);
  }
}
