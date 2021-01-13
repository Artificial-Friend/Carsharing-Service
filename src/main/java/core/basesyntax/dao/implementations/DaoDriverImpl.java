package core.basesyntax.dao.implementations;

import core.basesyntax.dao.DaoDriver;
import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class DaoDriverImpl implements DaoDriver {
    @Override
    public Driver create(Driver item) {
        Storage.saveDriver(item);
        return item;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Optional.ofNullable(Storage.drivers.get(id));
    }

    @Override
    public List<Driver> getAll() {
        return new ArrayList<>(Storage.drivers.values());
    }

    @Override
    public Driver update(Driver item) {
        Storage.drivers.replace(item.getId(), item);
        return Storage.drivers.get(item.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (Storage.drivers. containsKey(id)) {
            Storage.drivers.remove(id);
            return true;
        }
        return false;
    }
}
