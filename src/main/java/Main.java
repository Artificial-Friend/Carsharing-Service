import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;

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
        Manufacturer manufacturer5 = new Manufacturer("vehicle5", "germany");
        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        manufacturerService.create(manufacturer4);
        manufacturerService.create(manufacturer5);
        manufacturerService.getAll().forEach(System.out::println);
        Manufacturer changedManufacturer1 = new Manufacturer("blabla", "blabla");
        changedManufacturer1.setId(5L);
        manufacturerService.update(changedManufacturer1);
        manufacturerService.delete(1L);
        manufacturerService.get(1L);
    }
}
