package core.basesyntax.service;

import core.basesyntax.dao.DaoManufacturer;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Manufacturer;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    DaoManufacturer dao;

    @Override
    public Manufacturer create(Manufacturer item) {
        return dao.create(item);
    }

    @Override
    public Manufacturer get(Long id) {
        return dao.get(id).get();
    }

    @Override
    public List<Manufacturer> getAll() {
        return dao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return dao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
