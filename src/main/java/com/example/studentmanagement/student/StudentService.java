package com.example.studentmanagement.student;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        student.setId(null);
        if (repository.existsByEmailIgnoreCase(student.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A student with this email already exists");
        }
        return repository.save(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student update(Long id, Student details) {
        Student existing = findById(id);
        if (repository.existsByEmailIgnoreCaseAndIdNot(details.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A student with this email already exists");
        }
        existing.setName(details.getName());
        existing.setEmail(details.getEmail());
        existing.setBranch(details.getBranch());
        existing.setYear(details.getYear());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
