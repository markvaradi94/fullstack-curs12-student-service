package ro.fasttrackit.curs12.student.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs12.student.service.model.entity.StudentEntity;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
}
