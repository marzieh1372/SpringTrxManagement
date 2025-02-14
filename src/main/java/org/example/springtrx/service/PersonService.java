package org.example.springtrx.service;

import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Account;
import org.example.springtrx.model.entity.Person;

import org.example.springtrx.repo.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private AccountService accountService;

    public PersonService(PersonRepository personRepository, AccountService accountService) {
        this.personRepository = personRepository;
        this.accountService = accountService;
    }


    //***********************************************************

    /**
     * In this example when the saving account method return an exception, person have been saved because
     * account trx started in another trx
     * @param personDto
     * @return
     */
    public Person savePersonAndAccount(PersonDto personDto){
        Person person = new Person();
        person.setName(personDto.getName());
        person.setFamily(personDto.getFamily());
        Person savedPerson = personRepository.save(person);
        //-----------------------------
        Account account = new Account();
        account.setAccountNumber("852555");
        Account savedAccount=accountService.saveAccount(account);
        savedPerson.setAccount(savedAccount);

        return savedPerson;
    }

    //************************************************************

}
