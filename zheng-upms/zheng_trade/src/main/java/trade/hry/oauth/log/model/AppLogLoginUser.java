
package trade.hry.oauth.log.model;

import trade.hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_log_login_customer")
public class AppLogLoginUser
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "loginTime")
    private Date loginTime;

    @Column(name = "ip")
    private String ip;

    @Column(name = "isLogin")
    private Integer isLogin;
}
