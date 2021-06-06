package ro.fasttrackit.curs12.student.service.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs12.student.service.model.api.Student;
import ro.fasttrackit.curs12.student.service.model.entity.StudentEntity;

@Component
public class StudentMapper implements Mapper<Student, StudentEntity> {
    @Override
    public Student toApi(StudentEntity source) {
        if (source == null) {
            return null;
        }

        var target = new Student();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setAge(source.getAge());

        return target;
    }

    @Override
    public StudentEntity toEntity(Student source) {
        if (source == null) {
            return null;
        }

        var target = new StudentEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setAge(source.getAge());

        return target;
    }
}
