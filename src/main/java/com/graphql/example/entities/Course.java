package com.graphql.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String teacher;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course", cascade = CascadeType.ALL, targetEntity = Student.class)
    private List<Student> studentList;
}