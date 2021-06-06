package ro.fasttrackit.curs12.student.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs12.student.service.exceptions.StudentNotFoundException;
import ro.fasttrackit.curs12.student.service.model.api.Student;
import ro.fasttrackit.curs12.student.service.model.entity.StudentEntity;
import ro.fasttrackit.curs12.student.service.model.filters.StudentFilters;
import ro.fasttrackit.curs12.student.service.model.mapper.StudentMapper;
import ro.fasttrackit.curs12.student.service.repository.StudentDao;
import ro.fasttrackit.curs12.student.service.repository.StudentRepository;

import java.util.List;

import static java.util.Collections.unmodifiableList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper mapper;
    private final StudentDao studentDao;
    private final StudentRepository studentRepository;
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public List<Student> findAllStudents(StudentFilters filters) {
        return mapper.toApi(
                unmodifiableList(studentDao.findAllStudents(filters))
        );
    }

    public Student findStudent(String studentId) {
        return mapper.toApi(getOrThrow(studentId));
    }

    public Student addStudent(Student newStudent) {
        return mapper.toApi(
                studentRepository.save(mapper.toEntity(newStudent))
        );
    }

    public Student deleteStudent(String studentId) {
        StudentEntity studentToDelete = getOrThrow(studentId);
        studentRepository.delete(studentToDelete);
        publishToStudentRemoval(studentToDelete.getId());
        return mapper.toApi(studentToDelete);
    }

    private void publishToStudentRemoval(String studentId) {
        rabbitTemplate.convertAndSend(
                fanoutExchange.getName(),
                "studentRemoval",
                studentId
        );
    }

    private StudentEntity getOrThrow(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Could not find student"));
    }
}
