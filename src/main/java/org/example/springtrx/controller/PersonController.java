package org.example.springtrx.controller;


import org.example.springtrx.model.dto.PersonDto;
import org.example.springtrx.model.entity.Person;
import org.example.springtrx.service.PersonPropagation1Service;
import org.example.springtrx.service.PersonPropagation2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {


    private final PersonPropagation1Service personPropagation1Service;
    private final PersonPropagation2Service personPropagation2Service;

    public PersonController(PersonPropagation1Service personPropagation1Service, PersonPropagation2Service personPropagation2Service) {
        this.personPropagation1Service = personPropagation1Service;
        this.personPropagation2Service = personPropagation2Service;
    }


    //**************************************************************************************

    @PostMapping("trx-default/{hasException}")
    public ResponseEntity<Person> savePersonAndAccountTrxDefault(@RequestBody PersonDto personDto
            , @PathVariable("hasException") boolean hasException) {
        return new ResponseEntity<Person>(personPropagation1Service.savePersonAndAccountSimple(personDto, hasException),
                HttpStatus.CREATED);
    }

    //**************************************************************************************

    @PostMapping("/trx-new/{hasException}")
    public ResponseEntity<Person> savePersonAndAccountTrxNew(@RequestBody PersonDto personDto
            , @PathVariable("hasException") boolean hasException) {
        return new ResponseEntity<Person>(personPropagation1Service.savePersonAndAccountNew(personDto, hasException), HttpStatus.CREATED);
    }

    //**************************************************************************************

    @PostMapping("trx-nested/{hasException}")
    public ResponseEntity<Person> savePersonAndSendNotificationTrxNested(@RequestBody PersonDto personDto
            , @PathVariable("hasException") boolean hasException) {
        return new ResponseEntity<Person>(personPropagation2Service.registerUserWithNestedTrx(personDto, hasException), HttpStatus.CREATED);
    }



}
