package hry.ex.digitalmoneyAccount.service;

import hry.core.mvc.service.base.BaseService;
import hry.ex.digitalmoneyAccount.model.ExDigitalmoneyAccount;

import java.util.List;

public abstract interface ExDigitalmoneyAccountService
        extends BaseService<ExDigitalmoneyAccount, Long> {
    ExDigitalmoneyAccount getExDigitalmoneyAccountByPublicKey(String paramString1, String paramString2);

    boolean isexist(String paramString1, String paramString2);

    List<String> listAccountNumByCoinCode(String paramString);

    List<String> listPublicKeyByCoinCode(String paramString);

    String getEthPublicKeyByparams(String paramString);

    List<String> listEtherpublickey();

    ExDigitalmoneyAccount getAccountByAccountNumber(String paramString);
}