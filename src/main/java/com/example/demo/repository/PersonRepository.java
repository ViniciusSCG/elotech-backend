package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Person;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Integer>, QuerydslPredicateExecutor<Person> {

    Optional<Person> findFirstByCpf(String cpf);

}
