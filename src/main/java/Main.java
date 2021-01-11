import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("core.basesyntax");

    public static void main(String[] args) {
        ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
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
        manufacturerService.get(3L);
        manufacturerService.delete(2L);
        manufacturerService.update(manufacturer1);
        List<Manufacturer> all = manufacturerService.getAll();
        all.forEach(System.out::println);
    }
}

