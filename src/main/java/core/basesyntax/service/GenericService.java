package core.basesyntax.service;

import java.util.List;

public interface GenericService<T, I> {
    T create(T item);

    T get(I id);

    List<T> getAll();

    T update(T item);

    boolean delete(Long id);
}
