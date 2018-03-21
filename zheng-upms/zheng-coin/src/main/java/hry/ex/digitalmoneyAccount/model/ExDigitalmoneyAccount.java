
package hry.ex.digitalmoneyAccount.model;

import hry.core.mvc.model.BaseModel;
import lombok.Data;

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

    @Column(name = "version")
    private Integer version;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "hotMoney")
    private BigDecimal hotMoney;

    @Column(name = "coldMoney")
    private BigDecimal coldMoney;

    @Column(name = "userName")
    private String userName;

    @Column(name = "accountNum")
    private String accountNum;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "publicKey")
    private String publicKey;

    @Column(name = "privateKey")
    private String privateKey;

    @Column(name = "lendMoney")
    private BigDecimal lendMoney;

    @Column(name = "coinName")
    private String coinName;

    @Column(name = "coinCode")
    private String coinCode;

    @Column(name = "website")
    private String website;

    @Column(name = "psitioNaveragePrice")
    private BigDecimal psitioNaveragePrice;

    @Column(name = "psitioProtectPrice")
    private BigDecimal psitioProtectPrice;

    @Column(name = "sumCost")
    private BigDecimal sumCost;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "disableMoney")
    private BigDecimal disableMoney;

    @Column(name = "surname")
    private String surname;
}
