
package trade.hry.customer.user.model;

import lombok.Data;
import trade.hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_customer")
@Data
public class AppCustomer
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "passWord")
    private String passWord;

    @Column(name = "accountPassWord")
    private String accountPassWord;

    @Column(name = "isLock")
    private Integer isLock;

    @Column(name = "lockTime")
    private Date lockTime;

    @Column(name = "isChange")
    private Integer isChange;

    @Column(name = "isDelete")
    private Integer isDelete;

    @Column(name = "isReal")
    private Integer isReal;

    @Column(name = "isRealUsd")
    private Integer isRealUsd;

    @Column(name = "salt")
    private String salt;

    @Column(name = "userCode")
    private String userCode;

    @Column(name = "integral")
    private Integer integral;

    @Column(name = "type")
    private String type;

    @Transient
    private Object appPersonInfo;

    @Column(name = "referralCode")
    private String referralCode;

    @Column(name = "hasEmail")
    public Integer hasEmail;

    @Column(name = "googleKey")
    private String googleKey;

    @Column(name = "googleState")
    private Integer googleState;

    @Column(name = "googleDate")
    private Date googleDate;

    @Column(name = "isProving")
    private Integer isProving;

    @Column(name = "messIp")
    private String messIp;

    @Column(name = "passDate")
    private Date passDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phoneState")
    private Integer phoneState;

    @Column(name = "openPhone")
    private Integer openPhone;

    @Column(name = "states")
    private Integer states;

    @Column(name = "uuid")
    private String uuid;

    @Transient
    private Long customerId;

    @Transient
    private String truename;

    @Transient
    private String surname;

    @Column(name = "commendCode")
    private String commendCode;

    @Column(name = "receCode")
    private String receCode;
}
