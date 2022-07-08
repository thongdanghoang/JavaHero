package ctrl.map;

public interface IMapping<E, D> {

    D toDTO(E e);

    E toEntity(D t);
}

