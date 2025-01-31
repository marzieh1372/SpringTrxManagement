package org.example.springtrx.controller;


import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //**************************************************************************************

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        return new ResponseEntity<Person>(personService.savePerson(person),HttpStatus.CREATED);
    }

}
