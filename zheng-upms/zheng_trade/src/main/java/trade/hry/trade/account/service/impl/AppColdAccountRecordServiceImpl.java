
package trade.hry.trade.account.service.impl;


import hry.account.fund.model.AppColdAccountRecord;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import trade.hry.trade.account.service.AppColdAccountRecordService;

import javax.annotation.Resource;


@Service("appColdAccountRecordService")
public class AppColdAccountRecordServiceImpl
        extends BaseServiceImpl<AppColdAccountRecord, Long>
        implements AppColdAccountRecordService {

    @Resource(name = "appColdAccountRecordDao")
    public void setDao(BaseDao<AppColdAccountRecord, Long> dao) {
        /* 29 */
        this.dao = dao;

    }

}