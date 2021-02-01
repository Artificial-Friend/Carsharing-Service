package core.basesyntax.service.implementations;

import core.basesyntax.dao.DaoManufacturer;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private DaoManufacturer dao;

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
    public Manufacturer update(Manufacturer item) {
        return dao.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
