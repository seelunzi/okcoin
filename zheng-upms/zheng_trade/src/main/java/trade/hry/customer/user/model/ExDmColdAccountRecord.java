
package trade.hry.customer.user.model;

import trade.hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;


@Table(name = "ex_dm_cold_account_record")
public class ExDmColdAccountRecord
        extends BaseModel {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "recordType")
    private Integer recordType;

    @Column(name = "transactionMoney")
    private BigDecimal transactionMoney;

    @Column(name = "transactionNum")
    private String transactionNum;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remark")
    private String remark;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "coinCode")
    private String coinCode;

    @Column(name = "website")
    private String website;
}
