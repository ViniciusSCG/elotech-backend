package com.example.demo.dto;

import lombok.Builder;

public class ContactRecord {

        @Builder
        public record ContactCreateRequest(
                        String email,
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
