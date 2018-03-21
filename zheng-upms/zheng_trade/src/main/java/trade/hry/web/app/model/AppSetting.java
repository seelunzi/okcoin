
package trade.hry.web.app.model;


import hry.core.mvc.model.BaseModel;

import javax.persistence.*;


@Table(name = "app_setting")
public class AppSetting
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Long id;

    @Column(name = "propertiesName")
    protected String propertiesName;

    @Column(name = "isOpen")
    protected Integer isOpen;

    @Column(name = "propertiesUrl")
    protected String propertiesUrl;

    @Column(name = "remark")
    protected String remark;
}