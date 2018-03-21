
package trade.hry.manage.init.model;

import trade.hry.core.mvc.model.MgrBaseModel;

import javax.persistence.*;


@Table(name = "mgr_app_resource")
public class MgrAppResource
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

    @Column(name = "className")
    private String className;

    @Column(name = "methodName")
    private String methodName;

    @Column(name = "sysName")
    private String sysName;

    @Column(name = "isLock")
    private String isLock;

    @Column(name = "isDelete")
    private String isDelete;
}
