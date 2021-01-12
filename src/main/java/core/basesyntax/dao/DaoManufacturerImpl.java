package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Dao
public class DaoManufacturerImpl implements DaoManufacturer {
    @Override
    public Manufacturer create(Manufacturer item) {
        Storage.save(item);
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.ofNullable(Storage.get(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer item) {
        return Storage.replace(item);
    }

    @Override
    public boolean delete(Long id) {
        Storage.remove(id);
        return Storage.get(id) == null;
    }
}
