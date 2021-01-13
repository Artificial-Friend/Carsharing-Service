package core.basesyntax.service.implementations;

import core.basesyntax.dao.DaoCar;
import core.basesyntax.dao.DaoDriver;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.service.CarService;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private DaoCar daoCar;
    @Inject
    private DaoDriver daoDriver;

    @Override
    public Car create(Car item) {
        return daoCar.create(item);
    }

    @Override
    public Car get(Long id) {
        return daoCar.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return daoCar.getAll();
    }

    @Override
    public Car update(Car item) {
        return daoCar.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return daoCar.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
        daoCar.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        daoCar.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        Optional<Driver> driver = daoDriver.get(driverId);
        List<Car> cars = daoCar.getAll();
        cars.removeIf(car -> !car.getDrivers().contains(driver));
        return cars;
    }
}
