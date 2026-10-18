package com.example.studentmanagement.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String branch;

    @NotNull @Min(1)
    @Column(name = "study_year", nullable = false)
    private Integer year;

    public Student() {}
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBranch() { return branch; }
    public Integer getYear() { return year; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setYear(Integer year) { this.year = year; }
}
