package com.example.demo.controller;

import com.example.demo.dto.ContactRecord.ContactCreateRequest;
import com.example.demo.dto.PersonRecord.PersonCreateRequest;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    public void testCreatePerson() throws Exception {
        PersonCreateRequest request = PersonCreateRequest.builder()
                .name("Fulano")
                .cpf("06219986059")
                .birthdate(LocalDate.of(2000, 1, 1))
                .contacts(
                        List.of(ContactCreateRequest.builder().email("fulano@gmail.com").phone("123456789").build()))
                .build();

        mockMvc.perform(post("/person/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(personService, times(1)).create(any(PersonCreateRequest.class));
    }

    @Test
    public void testCreatePersonWithInvalidCPF() throws Exception {
        PersonCreateRequest request = PersonCreateRequest.builder()
                .name("Fulano")
                .cpf("3122312312")
                .birthdate(LocalDate.of(2000, 1, 1))
                .contacts(
                        List.of(ContactCreateRequest.builder().email("invalidcpf@gmail.com").phone("123456789")
                                .build()))
                .build();

        mockMvc.perform(post("/person/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(personService, never()).create(any(PersonCreateRequest.class));
    }

    @Test
    public void testCreatePersonWithInvalidEmail() throws Exception {
        PersonCreateRequest request = PersonCreateRequest.builder()
                .name("Fulano")
                .cpf("06219986059")
                .birthdate(LocalDate.of(2000, 1, 1))
                .contacts(
                        List.of(ContactCreateRequest.builder().email("invalidemail").phone("123456789").build()))
                .build();

        mockMvc.perform(post("/person/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(personService, never()).create(any(PersonCreateRequest.class));
    }

}