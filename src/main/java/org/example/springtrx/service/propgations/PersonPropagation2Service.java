package org.example.springtrx.service.propgations;

import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.repo.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonPropagation2Service {

    private final PersonRepository personRepository;
    private final NotificationService notificationService;
    public PersonPropagation2Service(PersonRepository personRepository, NotificationService notificationService) {
        this.personRepository = personRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Person registerUserWithNestedTrx(PersonDto personDto, boolean hasException) {

        // save person
        Person person = new Person();
        person.setName(personDto.getName());
        person.setFamily(personDto.getFamily());
        Person savedPerson = personRepository.save(person);

        //call notification with Nested propagation
        notificationService.sendWelcomeEmail(hasException);

        //TODO another tran

        return savedPerson;
    }
}
