package trade.hry.trade.account.dao;


import trade.hry.account.fund.model.AppColdAccountRecord;

import java.util.List;

public interface AppColdAccountRecordDao
        extends BaseDao<AppColdAccountRecord, Long> {
    void insertRecord(List<AppColdAccountRecord> paramList);
}
