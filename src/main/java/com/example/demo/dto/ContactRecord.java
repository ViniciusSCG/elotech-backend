package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;

public class ContactRecord {

        @Builder
        public record ContactCreateRequest(
                        @Email String email,
                        String phone,
                        Integer personId) {

        }

        @Builder
        public record ContactResponse(
                        Integer id,
                        String email,
                        String phone) {

        }

        @Builder
        public record ContactUpdateRequest(
                        Integer id,
                        String email,
                        String phone,
                        Integer personId) {

        }

}
