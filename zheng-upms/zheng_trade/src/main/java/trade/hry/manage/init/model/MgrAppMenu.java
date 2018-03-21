
package trade.hry.manage.init.model;

import lombok.Data;
import trade.hry.core.mvc.model.MgrBaseModel;

import javax.persistence.*;

@Table(name = "mgr_app_menu")
@Data
public class MgrAppMenu
        extends MgrBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "mkey")
    private String mkey;

    @Column(name = "pkey")
    private String pkey;

    @Column(name = "appName")
    private String appName;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "shiroUrl")
    private String shiroUrl;

    @Column(name = "isOpen")
    private String isOpen;

    @Column(name = "isOutLink")
    private String isOutLink;

    @Column(name = "orderNo")
    private String orderNo;

    @Column(name = "type")
    private String type;
}
