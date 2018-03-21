
package trade.hry.trade.MQmanager;

import hry.core.util.serialize.Mapper;
import hry.core.util.sys.ContextUtil;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.EntrustTrade;
import trade.hry.trade.mq.service.MessageProducer;

import java.util.List;

public class MQEnter {

    public static void pushExEntrustMQ(EntrustTrade exEntrust) {
        MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
        String exentrust = Mapper.objectToJson(exEntrust);
        messageProducer.toTrade(exentrust);
    }

    public static void pushDealFundMQ(List<Accountadd> aadds) {
        MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
        String accountadds = Mapper.objectToJson(aadds);
        messageProducer.toAccount(accountadds);
    }
}
