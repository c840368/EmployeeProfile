package com.bnsf.wum.empprofile;

import com.bnsf.wum.empprofile.msgqueue.EmployeeMessageSender;
import com.bnsf.wum.empprofile.msgqueue.MsgQueueConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to send the message in queue
 */
@SpringBootApplication
public class MessageSenderMainApplication {

    private static final Logger log = LoggerFactory.getLogger(MessageSenderMainApplication.class);

	public static void main(String[] args) {
    ConfigurableApplicationContext context=null;
    try {
       log.info("Message sender   Starting....");

       // sennder code to send the message
       context = SpringApplication.run(MsgQueueConfig.class, args);
       EmployeeMessageSender messageCreator = context.getBean(EmployeeMessageSender.class);
       JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
       log.info("---Sending Message-----");

        jmsTemplate.send("EmployeeQueue", messageCreator);

    }catch (Exception e) {
            log.error("error starting main application:"+e.getMessage());
        }
        finally{
        context.close();
    }

	}
}

