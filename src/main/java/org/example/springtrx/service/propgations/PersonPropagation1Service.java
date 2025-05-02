package org.example.springtrx.service.propgations;

import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Account;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.repo.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonPropagation1Service {
    private PersonRepository personRepository;
    private AccountPropagation1Service accountPropagation1Service;

    public PersonPropagation1Service(PersonRepository personRepository, AccountPropagation1Service accountPropagation1Service) {
        this.personRepository = personRepository;
        this.accountPropagation1Service = accountPropagation1Service;
    }

    //************************************************************

    /**
     * In this example when the saving account method return an exception,
     * person don't have been saved because
     * account and person saved in one account
     * @param personDto
     * @param hasException
     * @return
     */
    @Transactional
    public Person savePersonAndAccountSimple(PersonDto personDto,boolean hasException){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setFamily(personDto.getFamily());
        Person savedPerson = personRepository.save(person);
        //-----------------------------
        Account account = new Account();
        account.setAccountNumber("26353");
        Account savedAccount = accountPropagation1Service.saveAccountWithoutTRX(account, hasException);
        savedPerson.setAccount(savedAccount);

        return savedPerson;
    }

    //*************************************************************

    /**
     * In this example when the saving account method return an exception,
     * person have been saved because
     * account trx started in another trx
     * @param personDto
     * @return
     */
    @Transactional
    public Person savePersonAndAccountNew(PersonDto personDto,boolean hasException){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setFamily(personDto.getFamily());
        Person savedPerson = personRepository.save(person);
        //-----------------------------
        Account account = new Account();
        account.setAccountNumber("852555");
        Account savedAccount = accountPropagation1Service.saveAccountWithRequiredNew(account, hasException);
        //savedPerson.setAccount(savedAccount);

        //save address

        return savedPerson;
    }



}
