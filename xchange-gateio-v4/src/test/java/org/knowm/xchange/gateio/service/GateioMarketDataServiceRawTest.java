package org.knowm.xchange.gateio.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.gateio.GateioExchangeWiremock;
import org.knowm.xchange.gateio.dto.marketdata.GateioCurrencyChain;
import org.knowm.xchange.gateio.dto.marketdata.GateioCurrencyInfo;
import org.knowm.xchange.gateio.dto.marketdata.GateioMarketInfoWrapper;
import org.knowm.xchange.gateio.dto.marketdata.GateioMarketInfoWrapper.GateioMarketInfo;
import org.knowm.xchange.gateio.dto.marketdata.GateioOrderBook;
import org.knowm.xchange.gateio.dto.marketdata.GateioOrderBook.PriceSizeEntry;

public class GateioMarketDataServiceRawTest extends GateioExchangeWiremock {

  GateioMarketDataServiceRaw gateioMarketDataServiceRaw = (GateioMarketDataServiceRaw) exchange.getMarketDataService();

  @Test
  public void getMarketInfo_valid() throws IOException {

    Map<CurrencyPair, GateioMarketInfoWrapper.GateioMarketInfo> expected = new HashMap<>();
    expected.put(CurrencyPair.BTC_USDT,
        new GateioMarketInfo(CurrencyPair.BTC_USDT, 1, new BigDecimal("0.00010"), new BigDecimal("0.2")));
    expected.put(CurrencyPair.ETH_USDT,
        new GateioMarketInfo(CurrencyPair.ETH_USDT, 2, new BigDecimal("0.001"), new BigDecimal("0.2")));

    Map<CurrencyPair, GateioMarketInfoWrapper.GateioMarketInfo> actual = gateioMarketDataServiceRaw.getMarketInfo();
    assertThat(actual).isEqualTo(expected);
  }


  @Test
  public void getCurrencies_valid() {
    List<GateioCurrencyInfo> actual = gateioMarketDataServiceRaw.getCurrencies();

    assertThat(actual).hasSize(5);

    GateioCurrencyInfo actualBtc = actual.get(0);

    GateioCurrencyInfo expectedBtc = GateioCurrencyInfo.builder()
        .currency("BTC")
        .delisted(false)
        .withdrawDisabled(false)
        .withdrawDelayed(false)
        .depositDisabled(false)
        .tradeDisabled(false)
        .chain("BTC")
        .build();

    assertThat(actualBtc).usingRecursiveComparison().isEqualTo(expectedBtc);
  }


  @Test
  public void getGateioOrderBook_valid() {
    List<PriceSizeEntry> expectedAsks = new ArrayList<>();
    expectedAsks.add(PriceSizeEntry.builder()
        .price(new BigDecimal("200"))
        .size(BigDecimal.ONE)
        .build());
    expectedAsks.add(PriceSizeEntry.builder()
        .price(new BigDecimal("250"))
        .size(BigDecimal.TEN)
        .build());

    List<PriceSizeEntry> expectedBids = new ArrayList<>();
    expectedBids.add(PriceSizeEntry.builder()
        .price(new BigDecimal("150"))
        .size(BigDecimal.ONE)
        .build());
    expectedBids.add(PriceSizeEntry.builder()
        .price(new BigDecimal("100"))
        .size(BigDecimal.TEN)
        .build());

    GateioOrderBook expected = GateioOrderBook.builder()
        .generatedAt(Instant.parse("2023-05-14T22:10:10.493Z"))
        .updatedAt(Instant.parse("2023-05-14T22:10:10.263Z"))
        .asks(expectedAsks)
        .bids(expectedBids)
        .build();

    GateioOrderBook actual = gateioMarketDataServiceRaw.getGateioOrderBook(CurrencyPair.BTC_USDT);

    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }


  @Test
  public void getCurrencyChains_valid_result() {
    List<GateioCurrencyChain> expected = new ArrayList<>();
    expected.add(GateioCurrencyChain.builder()
        .chain("BTC")
        .chainNameCN("比特币 BRC20/Ordinals")
        .chainNameEN("Bitcoin BRC20/Ordinals")
        .disabled(false)
        .depositDisabled(false)
        .withdrawDisabled(false)
        .build());
    expected.add(GateioCurrencyChain.builder()
        .chain("HT")
        .chainNameCN("Heco")
        .chainNameEN("Heco")
        .disabled(true)
        .depositDisabled(true)
        .withdrawDisabled(true)
        .build());

    List<GateioCurrencyChain> actual = gateioMarketDataServiceRaw.getCurrencyChains(Currency.BTC);

    assertThat(actual).isEqualTo(expected);
  }
}