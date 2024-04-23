package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.example.demo.dto.ContactRecord.ContactCreateRequest;
import com.example.demo.dto.ContactRecord.ContactResponse;
import com.example.demo.dto.ContactRecord.ContactUpdateRequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

public class PersonRecord {
        @Builder
        public record PersonCreateRequest(
                        @NotEmpty String name,
                        @CPF @NotEmpty String cpf,
                        @NotEmpty LocalDate birthdate,
                        List<ContactCreateRequest> contacts) {
        }

        @Builder
        public record PersonUpdateRequest(
                        String name,
                        @CPF String cpf,
                        LocalDate birthdate,
                        List<ContactUpdateRequest> contacts) {
        }

        @Builder
        public record PersonResponse(
                        Integer id,
                        String name,
                        String cpf,
                        LocalDate birthdate,
                        List<ContactResponse> contacts) {
        }
}
