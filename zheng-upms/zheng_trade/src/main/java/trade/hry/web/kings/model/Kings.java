
package trade.hry.web.kings.model;


import hry.core.mvc.model.BaseModel;
import lombok.Data;

import javax.persistence.*;


@Table(name = "s_kings")
@Data
public class Kings
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "work")
    private String work;
}
