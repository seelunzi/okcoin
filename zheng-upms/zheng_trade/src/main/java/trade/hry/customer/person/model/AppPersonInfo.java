
package trade.hry.customer.person.model;


import lombok.Data;
import trade.hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "app_person_info")
@Data
public class AppPersonInfo
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "customerType")
    private Integer customerType;

    @Column(name = "mobilePhone")
    private String mobilePhone;

    @Column(name = "email")
    private String email;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "country")
    private String country;

    @Column(name = "cardType")
    private Integer cardType;

    @Column(name = "cardId")
    private String cardId;

    @Column(name = "customerSource")
    private Integer customerSource;

    @Column(name = "realTime")
    private Date realTime;

    @Column(name = "emailCode")
    private String emailCode;

    @Column(name = "cardIdUsd")
    private String cardIdUsd;

    @Column(name = "cardIdValidPeriod")
    private String cardIdValidPeriod;

    @Column(name = "postalAddress")
    private String postalAddress;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "postCode")
    private Integer postCode;

    @Column(name = "stage")
    private Integer stage;

    @Column(name = "contactsTel")
    private String contactsTel;

    @Column(name = "handIdCardUrl")
    private String handIdCardUrl;

    @Column(name = "idCardFrontUrl")
    private String idCardFrontUrl;

    @Column(name = "idCardBackUrl")
    private String idCardBackUrl;

    @Column(name = "handDealUrl")
    private String handDealUrl;

    @Column(name = "isExamine")
    private Integer isExamine;

    @Column(name = "eamineRefusalReason")
    private String eamineRefusalReason;

    @Column(name = "isCancle")
    private Integer isCancle;

    @Column(name = "cancleReason")
    private String cancleReason;

    @Column(name = "vipUserId")
    private Long vipUserId;

    @Column(name = "agentUserId")
    private Long agentUserId;

    @Column(name = "vipNumber")
    private String vipNumber;

    @Column(name = "agentNumber")
    private String agentNumber;

    @Column(name = "vipName")
    private String vipName;

    @Column(name = "agentName")
    private String agentName;

    @Column(name = "papersType")
    private String papersType;

    @Column(name = "withdrawCheckMoney")
    private BigDecimal withdrawCheckMoney;

    @Column(name = "jkAgentType")
    private Integer jkAgentType;

    @Column(name = "jkAgentName")
    private String jkAgentName;

    @Column(name = "jkAgentProvince")
    private String jkAgentProvince;

    @Column(name = "jkAgentProvinceCode")
    private String jkAgentProvinceCode;

    @Column(name = "jkAgentCity")
    private String jkAgentCity;

    @Column(name = "jkAgentCityCode")
    private String jkAgentCityCode;

    @Column(name = "jkAgentCounty")
    private String jkAgentCounty;

    @Column(name = "jkAgentCountyCode")
    private String jkAgentCountyCode;

    @Column(name = "jkApplyState")
    private Integer jkApplyState;

    @Column(name = "jkApplyType")
    private Integer jkApplyType;

    @Column(name = "jkApplyAuthorizationCode")
    private String jkApplyAuthorizationCode;

    @Column(name = "jkApplyAuthorizationCodeState")
    private Integer jkApplyAuthorizationCodeState;

    @Column(name = "jkApplyAgentProvince")
    private String jkApplyAgentProvince;

    @Column(name = "jkApplyAgentProvinceCode")
    private String jkApplyAgentProvinceCode;

    @Column(name = "jkApplyAgentCity")
    private String jkApplyAgentCity;

    @Column(name = "jkApplyAgentCityCode")
    private String jkApplyAgentCityCode;

    @Column(name = "jkApplyAgentCounty")
    private String jkApplyAgentCounty;

    @Column(name = "jkApplyAgentCountyCode")
    private String jkApplyAgentCountyCode;

    @Column(name = "isGivePerAgentRecommendCoin")
    private String isGivePerAgentRecommendCoin;

    @Column(name = "hasRecommendNum")
    private Integer hasRecommendNum;

    @Transient
    private BigDecimal totalAvailableMoney;

    @Transient
    private BigDecimal totalFrozenMoney;

    @Transient
    private BigDecimal totalRechargeMoney;

    @Transient
    private BigDecimal totalWithdrawalsMoney;

    @Transient
    private BigDecimal moneyChangeRate;

    @Transient
    private BigDecimal profitRate;

    @Column(name = "personCard")
    private String personCard;

    @Column(name = "frontpersonCard")
    private String frontpersonCard;

    @Column(name = "personBank")
    private String personBank;

    @Transient
    private String bankName;

    @Transient
    private String bankCardNumber;
}
