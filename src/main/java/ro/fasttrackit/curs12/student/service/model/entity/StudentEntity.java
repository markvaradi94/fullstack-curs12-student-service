package ro.fasttrackit.curs12.student.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(collection = "students")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    private String id = ObjectId.get().toHexString();

    @NotNull
    private String name;

    @NotNull
    private Integer age;
}
