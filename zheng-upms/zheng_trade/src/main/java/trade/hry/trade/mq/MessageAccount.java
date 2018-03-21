
package trade.hry.trade.mq;


import com.rabbitmq.client.Channel;
import hry.core.mvc.model.log.AppException;
import hry.core.mvc.service.log.AppExceptionService;
import hry.core.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import trade.hry.trade.entrust.service.TradeService;

import java.io.IOException;


public class MessageAccount
        implements ChannelAwareMessageListener {
    private Logger logger = Logger.getLogger(MessageAccount.class);

    public void onMessage(Message message, Channel channel) {
        TradeService tradeService = (TradeService) ContextUtil.getBean("tradeService");
        try {
            Boolean flag = tradeService.accountaddQueue(new String(message.getBody()));
            if (!flag.booleanValue()) {
                AppException exceptionLog = new AppException();
                exceptionLog.setName("mq==消息报错体==");
                AppExceptionService appExceptionService = (AppExceptionService) ContextUtil.getBean("appExceptionService");
                appExceptionService.save(exceptionLog);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        } catch (Exception e) {
            try {
                AppException exceptionLog = new AppException();
                exceptionLog.setName("mq==消息报错体2==");
                AppExceptionService appExceptionService = (AppExceptionService) ContextUtil.getBean("appExceptionService");
                appExceptionService.save(exceptionLog);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException a) {
                a.printStackTrace();
            }
        }
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e1) {
            this.logger.info("mq==channel.basicAck==确认失败");
            e1.printStackTrace();
        }
    }
}
