package com.example.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.dto.PersonRecord.PersonResponse;
import com.example.demo.dto.PersonRecord.PersonUpdateRequest;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public PersonResponse create(@RequestBody PersonCreateRequest personCreateRequest) {
        return personService.create(personCreateRequest);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PatchMapping("/update/{id}")
    public PersonResponse update(@RequestBody PersonUpdateRequest personUpdateRequest, @PathVariable Integer id) {
        return personService.update(personUpdateRequest, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/paginate")

    public Page<PersonResponse> list(@QuerydslPredicate(root = Person.class) Predicate predicate,
            Pageable pageable) {
        return personService.paginate(pageable, predicate);
    }

}
