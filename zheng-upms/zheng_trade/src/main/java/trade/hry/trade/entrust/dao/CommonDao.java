package trade.hry.trade.entrust.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.user.model.AppCustomer;
import hry.exchange.product.model.ExCointoCoin;
import hry.trade.redis.model.EntrustTrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract interface CommonDao
        extends BaseDao<EntrustTrade, Long> {
    AppCustomer getAppUserByuserName(@Param("userName") String paramString);

    List<ExCointoCoin> getExointocoinValid();

    List<AppCustomer> getAppUserAll();
}
