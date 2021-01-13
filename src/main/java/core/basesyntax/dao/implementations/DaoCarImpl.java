package core.basesyntax.dao.implementations;

import core.basesyntax.dao.DaoCar;
import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class DaoCarImpl implements DaoCar {
    @Override
    public Car create(Car item) {
        Storage.saveCar(item);
        return item;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Optional.ofNullable(Storage.cars.get(id));
    }

    @Override
    public List<Car> getAll() {
        return new ArrayList<>(Storage.cars.values());
    }

    @Override
    public Car update(Car item) {
        Storage.cars.replace(item.getId(), item);
        return Storage.cars.get(item.getId());
    }

    @Override
    public boolean delete(Long id) {
        if (Storage.cars. containsKey(id)) {
            Storage.cars.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return getAll()
                .stream()
                .filter(car -> car.getDrivers()
                        .stream()
                        .map(Driver::getId)
                        .collect(Collectors.toList()).contains(driverId))
                .collect(Collectors.toList());
    }
}
