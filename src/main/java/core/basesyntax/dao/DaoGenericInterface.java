package core.basesyntax.dao;

import java.util.List;
import java.util.Optional;

public interface DaoGenericInterface<T> {
    T create(T item);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T item);

    boolean delete(Long id);
}
