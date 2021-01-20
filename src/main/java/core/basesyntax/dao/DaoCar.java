package core.basesyntax.dao;

import core.basesyntax.model.Car;
import java.util.List;

public interface DaoCar extends GenericDao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}
