package ctrl.map;

import ctrl.dto.ClazzDTO;
import model.entity.Clazz;
import org.mapstruct.Mapper;

/**
 *
 * @author thomas
 */
@Mapper
public interface ClazzMapper {

    Clazz toEntity(ClazzDTO dto);

    ClazzDTO toDTO(Clazz entity);
}
