package core.basesyntax.dao.implementations;

import core.basesyntax.dao.DaoManufacturer;
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
        Storage.saveManufacturer(item);
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.ofNullable(Storage.manufacturers.get(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return new ArrayList<>(Storage.manufacturers.values());
    }

    @Override
    public Manufacturer update(Manufacturer item) {
        Storage.manufacturers.replace(item.getId(), item);
        return Storage.manufacturers.get(item.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (Storage.manufacturers. containsKey(id)) {
            Storage.manufacturers.remove(id);
            return true;
        }
        return false;
    }
}
