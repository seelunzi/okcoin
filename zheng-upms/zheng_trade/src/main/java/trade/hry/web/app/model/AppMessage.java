
package trade.hry.web.app.model;


import hry.core.mvc.model.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Table(name = "app_message")
@Data
public class AppMessage
        extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "sendUserName")
    private String sendUserName;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryId")
    private String categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "titleImage")
    private String titleImage;

    @Column(name = "sortTitle")
    private String sortTitle;

    @Column(name = "operator")
    private String operator;

    @Column(name = "isAll")
    private Integer isAll;

    @Column(name = "isSend")
    private Integer isSend;

    @Column(name = "sendDate")
    private Date sendDate;

    @Transient
    private MessageAsCustomer messageAsCustomer;
}
