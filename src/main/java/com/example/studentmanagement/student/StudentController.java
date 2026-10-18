package com.example.studentmanagement.student;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository students;
    public StudentController(StudentRepository students) { this.students = students; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@Valid @RequestBody Student student) {
        student.setId(null);
        return students.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() { return students.findAll(); }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) { return findStudent(id); }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student input) {
        Student existing = findStudent(id);
        existing.setName(input.getName());
        existing.setEmail(input.getEmail());
        existing.setBranch(input.getBranch());
        existing.setYear(input.getYear());
        return students.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        Student student = findStudent(id);
        students.delete(student);
    }

    private Student findStudent(Long id) {
        return students.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
}
