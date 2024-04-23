package com.example.demo.dto.mapper;

import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.dto.PersonRecord.PersonResponse;
import com.example.demo.entity.Person;

public class PersonMapper {
    public static Person toEntity(PersonCreateRequest personCreateRequest) {
        var person = new Person();
        person.setName(personCreateRequest.name());
        person.setCpf(personCreateRequest.cpf());
        person.setBirthdate(personCreateRequest.birthdate());

        person.setContacts(ContactMapper.toListEntity(personCreateRequest.contacts()));

        return person;
    }

    public static Person toEntityId(Integer id) {
        if (id == null) {
            return null;
        }
        var person = new Person();
        person.setId(id);
        return person;
    }

    public static PersonResponse toDTO(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .cpf(person.getCpf())
                .birthdate(person.getBirthdate())
                .contacts(ContactMapper.toListDTO(person.getContacts()))
                .build();
    }

}
