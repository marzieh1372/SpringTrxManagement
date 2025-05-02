package org.example.springtrx.service.propgations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {


    /**
     * If a transaction already exists.
     * Rollbacks in nested transactions will not affect the parent transaction.
     *The Propagation.NESTED option requires that the underlying database supports savepoints.
     * If you're using MySQL, it’s generally recommended to use Propagation.REQUIRES_NEW
     * for nested transactions instead,
     *  as MySQL does not support true nested transactions in the same way some other databases do.
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
