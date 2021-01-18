import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("core.basesyntax");

    public static void main(String[] args) {
        final ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        final DriverService driverService =
                (DriverService) injector.getInstance(DriverService.class);
        final CarService carService =
                (CarService) injector.getInstance(CarService.class);
        Manufacturer manufacturer1 = new Manufacturer("vehicle1", "china");
        Manufacturer manufacturer2 = new Manufacturer("vehicle2", "ukraine");
        Manufacturer manufacturer3 = new Manufacturer("vehicle3", "usa");
        Manufacturer manufacturer4 = new Manufacturer("vehicle4", "canada");
        Manufacturer manufacturer5 = new Manufacturer("UPDATED", "TEST");
        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        manufacturerService.create(manufacturer4);
        manufacturerService.create(manufacturer5);

        Car car1 = new Car("model1", manufacturer1);
        Car car2 = new Car("model2", manufacturer2);
        Car car3 = new Car("model3", manufacturer3);
        Car car4 = new Car("model4", manufacturer4);
        Car car5 = new Car("model5", manufacturer5);
        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        carService.create(car4);
        carService.create(car5);

        Driver driver1 = new Driver("Bob", "abc");
        Driver driver2 = new Driver("Alice", "def");
        Driver driver3 = new Driver("John", "ghj");
        Driver driver4 = new Driver("Bruce", "klm");
        Driver driver5 = new Driver("Max", "nop");
        Driver maximilian = new Driver("TEST DRIVEN", "DEVELOPMENT");
        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        driverService.create(driver4);
        driverService.create(driver5);
        driverService.create(maximilian);

        manufacturerService.getAll().forEach(System.out::println);
        Manufacturer changedManufacturer1 = new Manufacturer("blabla", "blabla");
        changedManufacturer1.setId(5L);
        manufacturerService.update(changedManufacturer1);
        //manufacturerService.delete(1L);
        manufacturerService.get(1L);

        carService.get(1L);
        carService.delete(4L);
        car1.setManufacturer(manufacturer5);
        carService.update(car1);
        carService.addDriverToCar(maximilian, car1);
        carService.removeDriverFromCar(driver1, car1);
        List<Car> allByDriver = carService.getAllByDriver(1L);
        allByDriver.forEach(System.out::println);
        List<Car> allCars = carService.getAll();
        allCars.forEach(System.out::println);

        driverService.get(1L);
        driverService.delete(2L);
        driver1.setName("REDACTED");
        driverService.update(driver1);
        List<Driver> allDrivers = driverService.getAll();
        allDrivers.forEach(System.out::println);
    }
}
