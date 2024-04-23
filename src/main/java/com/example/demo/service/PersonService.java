package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.dto.PersonRecord.PersonResponse;
import com.example.demo.dto.PersonRecord.PersonUpdateRequest;
import com.example.demo.entity.Person;
import com.querydsl.core.types.Predicate;

public interface PersonService {

    Optional<Person> findFirstByCpf(String cpf);

    PersonResponse create(PersonCreateRequest personCreateRequest);

    PersonResponse update(PersonUpdateRequest PersonUpdateRequest, Integer id);

    Page<PersonResponse> paginate(Pageable pageable, Predicate predicate);

}
