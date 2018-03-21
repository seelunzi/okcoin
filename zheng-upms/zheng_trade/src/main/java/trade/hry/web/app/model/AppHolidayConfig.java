
package trade.hry.web.app.model;


import hry.core.mvc.model.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "app_holiday_config")
@Data
public class AppHolidayConfig
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "beginDate")
    private Date beginDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "states")
    private Integer states;
}