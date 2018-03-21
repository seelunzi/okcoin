
package trade.hry.trade.mq;


import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import trade.hry.trade.mq.service.MessageProducer;

import javax.annotation.Resource;


public class MessageConsumer
        implements MessageListener {
    private Logger logger = Logger.getLogger(MessageConsumer.class);


    @Resource
    private MessageProducer messageProducer;


    public void onMessage(Message message) {
        this.logger.info("consumer receive message------->" + new String(message.getBody()));
        int i = 10 / 0;
    }

}