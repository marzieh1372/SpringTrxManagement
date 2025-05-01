package org.example.springtrx.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    /**
     * If a transaction already exists.
     * Rollbacks in nested transactions will not affect the parent transaction.
     *
     * @param hasException
     */
    @Transactional(propagation = Propagation.NESTED)
    public void sendWelcomeEmail(boolean hasException) {
        if (hasException) {
            throw new RuntimeException("Email can not be null");
        }
        //TODO send email from mailServer
        System.out.println("Send Email to person ");
    }
}
