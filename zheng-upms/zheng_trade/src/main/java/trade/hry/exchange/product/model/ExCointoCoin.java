
package trade.hry.exchange.product.model;


import trade.hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "ex_cointo_coin")
public class ExCointoCoin
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "coinCode")
    private String coinCode;

    @Column(name = "fixPriceCoinCode")
    private String fixPriceCoinCode;

    @Column(name = "fixPriceType")
    private Integer fixPriceType;

    @Column(name = "keepDecimalFixPrice")
    private Integer keepDecimalFixPrice;

    @Column(name = "state")
    private Integer state;

    @Column(name = "buyFeeRate")
    private BigDecimal buyFeeRate;

    @Column(name = "sellFeeRate")
    private BigDecimal sellFeeRate;

    @Column(name = "buyMinMoney")
    private BigDecimal buyMinMoney;

    @Column(name = "sellMinCoin")
    private BigDecimal sellMinCoin;

    @Column(name = "priceLimits")
    private BigDecimal priceLimits;

    @Column(name = "rose")
    private BigDecimal rose;

    @Column(name = "decline")
    private BigDecimal decline;

    @Column(name = "averagePrice")
    private BigDecimal averagePrice;

    @Column(name = "oneTimeOrderNum")
    private BigDecimal oneTimeOrderNum;

    @Column(name = "isSratAuto")
    private Integer isSratAuto;

    @Column(name = "isFromChbtc")
    private Integer isFromChbtc;

    @Column(name = "autoPrice")
    private BigDecimal autoPrice;

    @Column(name = "autoPriceFloat")
    private BigDecimal autoPriceFloat;

    @Column(name = "autoCount")
    private BigDecimal autoCount;

    @Column(name = "autoCountFloat")
    private BigDecimal autoCountFloat;

    @Column(name = "autoUsername")
    private String autoUsername;

    @Column(name = "atuoPriceType")
    private Integer atuoPriceType;

    @Column(name = "upFloatPer")
    private BigDecimal upFloatPer;

    @Column(name = "customerId")
    private Long customerId;

    @Transient
    private String yesterdayPrice;
}
