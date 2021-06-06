package ro.fasttrackit.curs12.student.service.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs12.student.service.model.api.Student;
import ro.fasttrackit.curs12.student.service.service.StudentService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final StudentService service;

    @Override
    public void run(String... args) throws Exception {
//        service.addStudent(Student.builder()
//                .name("Gigi")
//                .age(24)
//                .build());
//        service.addStudent(Student.builder()
//                .name("Joska")
//                .age(27)
//                .build());
//        service.addStudent(Student.builder()
//                .name("Gyuszi")
//                .age(51)
//                .build());
//        service.addStudent(Student.builder()
//                .name("Marta")
//                .age(63)
//                .build());

    }
}
