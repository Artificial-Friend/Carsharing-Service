package core.basesyntax.service.implementations;

import core.basesyntax.dao.DaoDriver;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Inject
    private DaoDriver dao;

    @Override
    public Driver create(Driver item) {
        return dao.create(item);
    }

    @Override
    public Driver get(Long id) {
        return dao.get(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return dao.getAll();
    }

    @Override
    public Driver update(Driver item) {
        return dao.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
