
package trade.hry.exchange.kline2.model;


import java.math.BigDecimal;


public class LastKLinePayload {
    private Long _id;
    private String symbolId = "btccny";

    private Long time;

    private String period = "1min";

    private BigDecimal priceOpen = new BigDecimal(0);

    private BigDecimal priceHigh = new BigDecimal(0);

    private BigDecimal priceLow = new BigDecimal(0);

    private BigDecimal priceLast = new BigDecimal(0);

    private BigDecimal amount = new BigDecimal(0);

    private BigDecimal dayTotalDealAmount = new BigDecimal(0);

    private BigDecimal volume = new BigDecimal(0);

    private BigDecimal count = new BigDecimal(0);

    private String startTime;

    private String endTime;
}
