
package hry.ex.digitalmoneyAccount.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.util.QueryFilter;
import hry.ex.digitalmoneyAccount.dao.ExDigitalmoneyAccountDao;
import hry.ex.digitalmoneyAccount.model.ExDigitalmoneyAccount;
import hry.ex.digitalmoneyAccount.service.ExDigitalmoneyAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("exDigitalmoneyAccountService")
public class ExDigitalmoneyAccountServiceImpl
        extends BaseServiceImpl<ExDigitalmoneyAccount, Long>
        implements ExDigitalmoneyAccountService {

    @Resource(name = "exDigitalmoneyAccountDao")
    public void setDao(BaseDao<ExDigitalmoneyAccount, Long> dao) {
        this.dao = dao;
    }


    public ExDigitalmoneyAccount getExDigitalmoneyAccountByPublicKey(String publicKey, String coinCode) {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("publicKey=", publicKey);
        ExDigitalmoneyAccount coinAccount = (ExDigitalmoneyAccount) get(filter);
        if (coinAccount != null) {
            return coinAccount;
        }
        return null;
    }

    public boolean isexist(String AccountNum, String coinType) {
        return ((ExDigitalmoneyAccountDao) this.dao).count(AccountNum, coinType) > 0;
    }


    public List<String> listAccountNumByCoinCode(String coinCode) {
        return ((ExDigitalmoneyAccountDao) this.dao).listAccountNumByCoinCode(coinCode);
    }

    public List<String> listPublicKeyByCoinCode(String coinCode) {
        return ((ExDigitalmoneyAccountDao) this.dao).listPublicKeyByCoinCode(coinCode);
    }

    public String getEthPublicKeyByparams(String userName) {
        return ((ExDigitalmoneyAccountDao) this.dao).getEthPublicKeyByparams(userName);
    }

    public List<String> listEtherpublickey() {
        return ((ExDigitalmoneyAccountDao) this.dao).listEtherpublickey();
    }

    public ExDigitalmoneyAccount getAccountByAccountNumber(String accountNumber) {
        return ((ExDigitalmoneyAccountDao) this.dao).getAccountByAccountNumber(accountNumber);
    }
}
