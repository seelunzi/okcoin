package trade.hry.trade.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import trade.hry.exchange.account.model.ExDigitalmoneyAccount;

import java.util.List;

public interface ExDigitalmoneyAccountDao
        extends BaseDao<ExDigitalmoneyAccount, Long> {
    void updateExDigitalmoneyAccount(List<ExDigitalmoneyAccountRedis> paramList);
}
