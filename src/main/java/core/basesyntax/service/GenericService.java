package core.basesyntax.service;

import java.util.List;

public interface GenericService<T> {
    T create(T item);

    T get(Long id);

    List<T> getAll();

    T update(T manufacturer);

    boolean delete(Long id);
}
