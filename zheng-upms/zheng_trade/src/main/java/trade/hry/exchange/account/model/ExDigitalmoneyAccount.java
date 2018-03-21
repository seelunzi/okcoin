
package trade.hry.exchange.account.model;

import lombok.Data;
import trade.hry.core.mvc.model.BaseModel;
import trade.hry.customer.person.model.AppPersonInfo;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "ex_digitalmoney_account")
@Data
public class ExDigitalmoneyAccount
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "hotMoney")
    private BigDecimal hotMoney;

    @Column(name = "coldMoney")
    private BigDecimal coldMoney;

    @Column(name = "userName")
    private String userName;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "coinCode")
    private String coinCode;

    @Column(name = "website")
    private String website;

    @Column(name = "coinName")
    private String coinName;

    @Column(name = "accountNum")
    private String accountNum;

    @Column(name = "status")
    private Integer status;

    @Column(name = "publicKey")
    private String publicKey;

    @Column(name = "privateKey")
    private String privateKey;

    @Column(name = "lendMoney")
    private BigDecimal lendMoney;

    @Column(name = "disableMoney")
    private BigDecimal disableMoney;

    @Column(name = "psitioNaveragePrice")
    private BigDecimal psitioNaveragePrice;

    @Column(name = "psitioProtectPrice")
    private BigDecimal psitioProtectPrice;

    @Column(name = "sumCost")
    private BigDecimal sumCost;

    @Transient
    private AppPersonInfo appPersonInfo;

    @Transient
    private BigDecimal positionAvePrice;

    @Transient
    private BigDecimal floatprofitandlossMoney;

    @Transient
    private BigDecimal floatprofitandlossMoneyRate;

    @Transient
    private BigDecimal rmbLendMoneySum;

    @Transient
    private BigDecimal sumRmbfund;

    @Transient
    private BigDecimal rmbAccountNetAsse;

    @Transient
    private BigDecimal CoinNum;
}
