package core.basesyntax.dao;

import java.util.List;
import java.util.Optional;

public interface DaoGenericInterface<T, I> {
    T create(T item);

    Optional<T> get(I id);

    List<T> getAll();

    T update(T item);

    boolean delete(I id);
}
