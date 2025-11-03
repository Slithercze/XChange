package org.knowm.xchange.dto.trade;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.instrument.Instrument;

import java.math.BigDecimal;
import java.util.Date;

/** Data object representing a user trade */
@Data
@SuperBuilder
public class UserTrade extends Trade {

  private static final long serialVersionUID = -3021617981214969292L;

  /** The id of the order responsible for execution of this trade */
  private String orderId;

  /** The fee that was charged by the exchange for this trade. */
  private BigDecimal feeAmount;

  /** The currency in which the fee was charged. */
  private Currency feeCurrency;

  /** The order reference id which has been added by the user on the order creation */
  private String orderUserReference;

  // In UserTrade.java
  public UserTrade(
          Order.OrderType type,
          BigDecimal originalAmount,
          Instrument instrument,
          BigDecimal price,
          Date timestamp,
          String id,
          String makerOrderId,
          String takerOrderId,
          String orderId,
          BigDecimal feeAmount,
          Currency feeCurrency,
          String orderUserReference) {

    super(type, originalAmount, instrument, price, timestamp, id, makerOrderId, takerOrderId);
    this.orderId = orderId;
    this.feeAmount = feeAmount;
    this.feeCurrency = feeCurrency;
    this.orderUserReference = orderUserReference;
  }
}
