package hry.ex.digitalmoneyAccount.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ex.digitalmoneyAccount.model.ExDigitalmoneyAccount;

import java.util.List;

public interface ExDigitalmoneyAccountDao
        extends BaseDao<ExDigitalmoneyAccount, Long> {
    int count(String paramString1, String paramString2);

    List<String> listAccountNumByCoinCode(String paramString);

    List<String> listPublicKeyByCoinCode(String paramString);

    String getEthPublicKeyByparams(String paramString);

    List<String> listEtherpublickey();

    ExDigitalmoneyAccount getAccountByAccountNumber(String paramString);
}
