package hry.ex.dmTransaction.service;

import hry.core.mvc.service.base.BaseService;
import hry.ex.dmTransaction.model.ExDmTransaction;

public interface ExDmTransactionService
        extends BaseService<ExDmTransaction, Long> {
    ExDmTransaction getExDmTransactionByOrderNo(String paramString);

    String rechargeCoin(ExDmTransaction paramExDmTransaction);
}