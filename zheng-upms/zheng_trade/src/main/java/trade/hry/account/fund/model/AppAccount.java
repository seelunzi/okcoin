
package trade.hry.account.fund.model;

import trade.hry.core.mvc.model.BaseModel;
import trade.hry.customer.person.model.AppPersonInfo;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "app_account")
public class AppAccount
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "website")
    private String website;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "hotMoney")
    private BigDecimal hotMoney;

    @Column(name = "coldMoney")
    private BigDecimal coldMoney;

    @Column(name = "rewardMoney")
    private BigDecimal rewardMoney;

    @Column(name = "hasRewardMoney")
    private BigDecimal hasRewardMoney;

    @Version
    private Integer version;

    @Column(name = "accountNum")
    private String accountNum;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "lendMoney")
    private BigDecimal lendMoney;

    @Transient
    private AppPersonInfo appPersonInfo;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "surname")
    private String surname;

    @Transient
    private BigDecimal rmbAccountNetAsse;

    @Transient
    private BigDecimal rmbLendMoneySum;

    @Transient
    private BigDecimal sumRmbfund;

    @Transient
    private BigDecimal sumCommissionMoney;

    @Transient
    private BigDecimal hotCurrency;

    @Transient
    private BigDecimal coldCurrency;
}
