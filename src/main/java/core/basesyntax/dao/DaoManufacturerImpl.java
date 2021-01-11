package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class DaoManufacturerImpl implements DaoManufacturer {
    @Override
    public Manufacturer create(Manufacturer item) {
        Storage.manufacturerId++;
        Storage.manufacturers.put(Storage.manufacturerId, item);
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.of(Storage.manufacturers.get(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return new ArrayList<>(Storage.manufacturers.values());
    }

    @Override
    public Manufacturer update(Manufacturer item) {
        return create(item);
        // what's the logic behind this?..
        // I get new value so I can't match it with old, what's the point?
    }

    @Override
    public boolean delete(Long id) {
        Storage.manufacturers.remove(id);
        return true;
    }
}
