
package trade.hry.core.mvc.model.constant;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "constantTip")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ConstantTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String tipkey;
    private String tipvalue;
}
