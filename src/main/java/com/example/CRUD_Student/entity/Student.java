package com.example.CRUD_Student.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 45, nullable = true)
    private String email;

    @Column(name = "first_name", length = 45, nullable = true)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = true)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "student")
    @JsonManagedReference
    private Set<Address> addresses;

}
