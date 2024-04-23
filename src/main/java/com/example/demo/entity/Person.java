package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON")
@SQLDelete(sql = "UPDATE person SET delete_date = NOW() WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Person extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(11)", nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
