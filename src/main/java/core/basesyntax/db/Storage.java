package core.basesyntax.db;

import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static final Map<Long, Manufacturer> manufacturers = new HashMap<>();
    public static final Map<Long, Car> cars = new HashMap<>();
    public static final Map<Long, Driver> drivers = new HashMap<>();
    private static Long manufacturerId = 0L;
    private static Long carId = 0L;
    private static Long driverId = 0L;

    public static void saveManufacturer(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturers.put(manufacturerId, manufacturer);
    }

    public static void saveCar(Car car) {
        carId++;
        car.setId(carId);
        cars.put(carId, car);
    }

    public static void saveDriver(Driver driver) {
        driverId++;
        driver.setId(driverId);
        drivers.put(driverId, driver);
    }

}
