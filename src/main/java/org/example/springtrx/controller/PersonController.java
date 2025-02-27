package org.example.springtrx.controller;


import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Account;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    //**************************************************************************************

    @PostMapping("trx-simple/{hasException}")
    public ResponseEntity<Person> savePersonAndAccountSimple(@RequestBody PersonDto personDto
            ,@PathVariable("hasException") boolean hasException){
        return new ResponseEntity<Person>(personService.savePersonAndAccountSimple(personDto,hasException),
                HttpStatus.CREATED);
    }

    //**************************************************************************************

    @PostMapping("/trx-new/{hasException}")
    public ResponseEntity<Person> savePersonAndAccountNew(@RequestBody PersonDto personDto
            ,@PathVariable("hasException") boolean hasException ){
        return new ResponseEntity<Person>(personService.savePersonAndAccountNew(personDto,hasException),HttpStatus.CREATED);
    }



}
