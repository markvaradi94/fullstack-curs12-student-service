package ro.fasttrackit.curs12.student.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs12.student.service.model.api.Student;
import ro.fasttrackit.curs12.student.service.model.filters.StudentFilters;
import ro.fasttrackit.curs12.student.service.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public List<Student> findAllStudents(StudentFilters filters) {
        return service.findAllStudents(filters);
    }

    @GetMapping("{studentId}")
    public Student findStudent(@PathVariable String studentId) {
        return service.findStudent(studentId);
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return service.addStudent(student);
    }

    @DeleteMapping("{studentId}")
    public Student deleteStudent(@PathVariable String studentId) {
        return service.deleteStudent(studentId);
    }
}
