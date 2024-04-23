package com.example.demo.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ContactRecord.ContactCreateRequest;
import com.example.demo.dto.ContactRecord.ContactResponse;
import com.example.demo.dto.ContactRecord.ContactUpdateRequest;
import com.example.demo.entity.Contact;

public class ContactMapper {
    public static Contact toEntity(ContactCreateRequest contactCreateRequest) {
        var contact = new Contact();
        contact.setEmail(contactCreateRequest.email());
        contact.setPhone(contactCreateRequest.phone());
        contact.setPerson(PersonMapper.toEntityId(contactCreateRequest.personId()));

        return contact;
    }

    public static Contact toEntityId(ContactUpdateRequest contactCreateRequest) {
        var contact = new Contact();
        contact.setId(contactCreateRequest.id());
        contact.setEmail(contactCreateRequest.email());
        contact.setPhone(contactCreateRequest.phone());
        contact.setPerson(PersonMapper.toEntityId(contactCreateRequest.personId()));

        return contact;
    }

    public static ContactResponse toDTO(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    public static List<ContactResponse> toListDTO(List<Contact> contacts) {
        var contactResponse = new ArrayList<ContactResponse>();
        for (var contact : contacts) {
            contactResponse.add(toDTO(contact));
        }

        return contactResponse;
    }

    public static List<Contact> toListEntity(List<ContactCreateRequest> contacts) {
        var contactList = new ArrayList<Contact>();
        for (var contact : contacts) {
            contactList.add(toEntity(contact));
        }

        return contactList;
    }

    public static List<Contact> toListEntityUpdate(List<ContactUpdateRequest> contacts) {
        var contactList = new ArrayList<Contact>();
        for (var contact : contacts) {
            contactList.add(toEntityId(contact));
        }

        return contactList;
    }

}
