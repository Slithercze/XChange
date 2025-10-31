package org.knowm.xchange.okex.dto.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/** Fills item for /api/v5/trade/fills and /api/v5/trade/fills-history */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(onlyExplicitlyIncluded = true)
public class OkexFill {

    @JsonProperty("instType")
    private String instType;      // SPOT/MARGIN/SWAP/FUTURES/OPTION
    @JsonProperty("instId")
    @ToString.Include(name = "symbol")
    private String instId;        // e.g. BTC-USDT
    @JsonProperty("tradeId")
    private String tradeId;       // last trade id
    @JsonProperty("ordId")
    private String ordId;         // order id
    @JsonProperty("clOrdId")
    private String clOrdId;       // client order id
    @JsonProperty("billId")
    private String billId;        // bill id
    @JsonProperty("subType")
    private String subType;       // transaction type (string code)
    @JsonProperty("tag")
    private String tag;           // order tag

    @JsonProperty("fillPx")
    private String filledPrice;        // last filled price
    @JsonProperty("fillSz")
    private String amount;        // last filled quantity
    @JsonProperty("fillIdxPx")
    private String fillIdxPx;     // index price at fill time (spot cross)
    @JsonProperty("fillPnl")
    private String fillPnl;       // PnL for closing trades (else "0")
    @JsonProperty("fillMarkPx")
    private String fillMarkPx;    // FUTURES/SWAP/OPTION

    @ToString.Include(name = "action")
    @JsonProperty("side")
    private String side;          // buy/sell
    @JsonProperty("posSide")
    private String posSide;       // long/short/net
    @JsonProperty("execType")
    private String execType;      // T (taker) / M (maker)

    @JsonProperty("feeCcy")
    private String feeCcy;        // fee currency
    @JsonProperty("fee")
    private String fee;           // negative = fee, positive = rebate
    @JsonProperty("feeRate")
    private String feeRate;       // SPOT/MARGIN only

    @JsonProperty("ts")
    private String ts;            // data gen time (ms, string)
    @JsonProperty("fillTime")
    private String fillTime;      // trade time (ms, string)

    @JsonProperty("tradeQuoteCcy")
    private String tradeQuoteCcy;

    @ToString.Include(name = "fillTimeReadable")
    public String getFillTimeReadable() {
        return formatMillis(fillTime);
    }

    private String formatMillis(String millisStr) {
        try {
            long ms = Long.parseLong(millisStr);
            return Instant.ofEpochMilli(ms)
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z"));
        } catch (Exception e) {
            return millisStr; // fallback if null or invalid
        }
    }
}