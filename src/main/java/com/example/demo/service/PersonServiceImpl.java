package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.base.BaseServiceImpl;
import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.dto.PersonRecord.PersonResponse;
import com.example.demo.dto.PersonRecord.PersonUpdateRequest;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.dto.mapper.PersonMapper;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.querydsl.core.types.Predicate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Optional<Person> findFirstByCpf(String cpf) {
        return repository.findFirstByCpf(cpf);
    }

    @Override
    @Transactional
    public PersonResponse create(PersonCreateRequest personCreateRequest) {

        repository.findFirstByCpf(personCreateRequest.cpf())
                .ifPresent(person -> {
                    throw new IllegalArgumentException("Pessoa já cadastrada!");
                });

        if (personCreateRequest.contacts() == null || personCreateRequest.contacts().isEmpty()) {
            throw new IllegalArgumentException("Contato não pode ser vazio!");
        }

        var personDB = repository.save(PersonMapper.toEntity(personCreateRequest));

        return PersonMapper.toDTO(personDB);

    }

    @Override
    public PersonResponse update(PersonUpdateRequest personUpdateRequest, Integer id) {

        var personDB = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada!"));
        BeanUtils.copyProperties(personUpdateRequest, personDB, this.getNullPropertyNames(personUpdateRequest));
        personDB.setContacts(ContactMapper.toListEntityUpdate(personUpdateRequest.contacts()));
        return PersonMapper.toDTO(repository.save(personDB));

    }

    @Override
    public Page<PersonResponse> paginate(Pageable pageable, Predicate predicate) {
        var page = repository.findAll(predicate, pageable);
        return page.map((person) -> PersonMapper.toDTO(person));
    }

    @Override
    public PersonResponse findById(Integer id) {
        var person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada!"));
        return PersonMapper.toDTO(person);
    }

    @Override
    public void delete(Integer id) {
        var person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada!"));
        repository.delete(person);
    }
}
