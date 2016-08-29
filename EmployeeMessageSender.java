package com.bnsf.wum.empprofile.msgqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class EmployeeMessageSender implements org.springframework.jms.core.MessageCreator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public Message createMessage(Session session) throws JMSException {
        log.info("Message send to Queue :"+fileReader());
        return session.createTextMessage(fileReader());
    }
/*
Method to read static data to feed into Queue
 */

    public String fileReader() {
        // The name of the file to open.
        String fileName = "Employee_Master_Feed.txt";

        // This will reference one line at a time
        String line = null;
        String message = "";
        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                message = (line.trim() + "\n" + message).trim();

            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            log.error("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            log.error("Error reading file '" + fileName + "'");
        }

        return message.trim();
    }
}
