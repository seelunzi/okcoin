package trade.hry.trade.account.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.trade.redis.model.AppAccountRedis;
import trade.hry.account.fund.model.AppAccount;

import java.util.List;

public abstract interface AppAccountDao
        extends BaseDao<AppAccount, Long> {
    public abstract void updateAppAccount(List<AppAccountRedis> paramList);
}


