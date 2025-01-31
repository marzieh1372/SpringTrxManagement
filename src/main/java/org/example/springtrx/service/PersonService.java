package org.example.springtrx.service;

import org.example.springtrx.model.entity.Person;
import org.example.springtrx.repo.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    //***********************************************************

    public Person savePerson(Person person){
        return personRepository.save(person);
    }
}
