package core.basesyntax.service.implementations;

import core.basesyntax.dao.DaoCar;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.service.CarService;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private DaoCar dao;

    @Override
    public Car create(Car item) {
        return dao.create(item);
    }

    @Override
    public Car get(Long id) {
        return dao.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return dao.getAll();
    }

    @Override
    public Car update(Car item) {
        return dao.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
        dao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        dao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return dao.getAllByDriver(driverId);
    }
}
