package ctrl.map;

import ctrl.dto.StudentDTO;
import model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author thomas
 */
@Mapper
public interface StudentMapper {

    @Mapping(source = "birthday", target = "birthday", dateFormat = "dd/MM/yyyy")
    StudentDTO toDTO(Student entity);

    Student toEntity(StudentDTO entity);
}
