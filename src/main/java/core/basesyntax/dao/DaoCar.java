package core.basesyntax.dao;

import core.basesyntax.model.Car;
import java.util.List;

public interface DaoCar extends DaoGenericInterface<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}
