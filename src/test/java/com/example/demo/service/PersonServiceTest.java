package com.example.demo.service;

import com.example.demo.dto.ContactRecord.ContactCreateRequest;
import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.entity.Person;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.PersonRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private ContactRepository repositoryContact;

    @InjectMocks
    private PersonServiceImpl service;

    @Test
    public void testCreatePersonWithFutureBirthdate() {
        PersonCreateRequest request = new PersonCreateRequest(
                "Test Name",
                "12345678900",
                LocalDate.now().plusDays(1),
                Collections.singletonList(new ContactCreateRequest("test@example.com", "1234567890", 1)));

        when(repository.findFirstByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.create(request));
    }

    @Test
    public void testCreatePersonWithEmptyContacts() {
        PersonCreateRequest request = new PersonCreateRequest(
                "Test Name",
                "12345678900",
                LocalDate.now(),
                Collections.emptyList());

        when(repository.findFirstByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.create(request));
    }

}