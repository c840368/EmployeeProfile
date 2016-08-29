package com.bnsf.wum.empprofile.msgqueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *     Class used by spring jmsTemplate for Active MQ default configuration
 */
@Configuration
@EnableAutoConfiguration
public class MsgQueueConfig {

    @Value("${queue.name}")
    public  String queueName;

  /*  @Bean
    EmployeeMsgReceiver msgReceiver() {
        return new EmployeeMsgReceiver();
    }*/
    @Bean
    org.springframework.jms.core.MessageCreator myMessageCreator() {
        return new EmployeeMessageSender();
    }


    /*@Bean
    MessageListenerAdapter messageListenerAdp(EmployeeMsgReceiver receiver) {
        MessageListenerAdapter messageListenerAdp = new MessageListenerAdapter(receiver);
        messageListenerAdp.setDefaultListenerMethod("receiveMessage");
        return messageListenerAdp;
    }

    @Bean
    SimpleMessageListenerContainer simpleMsgLisContainer(MessageListenerAdapter messageListenerAdp,
                                                         ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMsgLisContainer = new SimpleMessageListenerContainer();
        simpleMsgLisContainer.setMessageListener(messageListenerAdp);
        simpleMsgLisContainer.setConnectionFactory(connectionFactory);

        simpleMsgLisContainer.setDestinationName(queueName);
        return simpleMsgLisContainer;
    }*/

}