package ro.fasttrackit.curs12.student.service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs12.student.service.model.entity.StudentEntity;
import ro.fasttrackit.curs12.student.service.model.filters.StudentFilters;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class StudentDao {
    private final MongoTemplate mongo;

    public List<StudentEntity> findAllStudents(StudentFilters filters) {
        Query query = new Query();
        List<Criteria> criteria = buildCriteria(filters);

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        return mongo.find(query, StudentEntity.class);
    }

    private List<Criteria> buildCriteria(StudentFilters filters) {
        List<Criteria> criteria = new ArrayList<>();

        ofNullable(filters.getName())
                .ifPresent(name -> criteria.add(Criteria.where("name").regex(name, "i")));
        ofNullable(filters.getAge())
                .ifPresent(age -> criteria.add(Criteria.where("age").is(age)));
        ofNullable(filters.getMinAge())
                .ifPresent(minAge -> criteria.add(Criteria.where("age").gte(minAge)));
        ofNullable(filters.getMaxAge())
                .ifPresent(maxAge -> criteria.add(Criteria.where("age").lte(maxAge)));
        ofNullable(filters.getStudentId())
                .ifPresent(studentId -> criteria.add(Criteria.where("id").is(studentId)));
        return criteria;
    }
}
