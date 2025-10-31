package org.knowm.xchange.okex.dto.trade;

import lombok.Data;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.service.trade.params.TradeHistoryParamInstrument;
import org.knowm.xchange.service.trade.params.TradeHistoryParamLimit;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

import java.util.Date;

@Data
public class OkexTradeHistoryParamsV5
        implements TradeHistoryParamInstrument, TradeHistoryParamsTimeSpan, TradeHistoryParamLimit {

  private Instrument instrument;

  // TradeHistoryParamTimeSpan
  private Date startTime;   // inclusive or per-OKX docs
  private Date endTime;

  // TradeHistoryParamLimit
  private Integer limit;

  // Optional: OKX ordType filter ("limit","market", etc.)
  private String orderType;

  private CurrencyPair currencyPair;

  private String sinceOrderId;
}
