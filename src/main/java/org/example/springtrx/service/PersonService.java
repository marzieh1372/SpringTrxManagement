package org.example.springtrx.service;


import org.example.springtrx.model.entity.Account;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.repo.AccountRepository;
import org.example.springtrx.repo.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional // all the methods in this class will be transactional
public class PersonService {

    private PersonRepository personRepository;
    private AccountRepository accountRepository;

    public PersonService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }


    //***********************************************************

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    //@Transactional // all the methods in this class will be transactional
    public String testTransactional(Person person){
        Account account1 = new Account();
        account1.setAccountNumber("jjjjjjj");
        withdrawTransactional_Default(person, account1);
        return "transactional test passed!";
    }

    //@Transactional
    public void withdrawTransactional_Default(Person person, Account account){
        /** When we use @Transactional on top of a method it means:
         * if the second TRX doesn't save in DB the first one will also not save on DB. */
        personRepository.save(person); //TRX 1

        //accountRepository.save(account); //TRX 2

        createAccount(account);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAccount(Account account){ // Whoever calls this method will be in a new transaction
        accountRepository.save(account);
        if (true) {
            throw new RuntimeException("Error occurred!");
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void createPerson(Person person){
        /** I should have access to all the uncommited data of the Person.*/
        personRepository.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void withdrawTransactional_Propagation_(Person person, Account account){
        /** When we use @Transactional with Propagation.REQUIRES_NEW on top of a method
         * it will make a new transaction for second TRX and third TRX.
         * in this case the first TRX will be always commited. */
        personRepository.save(person); //TRX 1
        System.exit(0);
        accountRepository.save(account); //TRX 2
    }
}
