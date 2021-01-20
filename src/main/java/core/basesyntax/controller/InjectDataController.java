package core.basesyntax.controller;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.DriverService;
import core.basesyntax.service.ManufacturerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final ManufacturerService manufacturerService
            = (ManufacturerService) injector.getInstance(ManufacturerService.class);
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Manufacturer manufacturer1 = new Manufacturer("manufacturer1", "china");
        Manufacturer manufacturer2 = new Manufacturer("manufacturer2", "ukraine");
        Manufacturer manufacturer3 = new Manufacturer("manufacturer3", "usa");
        Manufacturer manufacturer4 = new Manufacturer("manufacturer4", "canada");
        Manufacturer manufacturer5 = new Manufacturer("manufacturer5", "mars");
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
        Driver driver3 = new Driver("John", "ghi");
        Driver driver4 = new Driver("Bruce", "jkl");
        Driver driver5 = new Driver("Max", "mno");
        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        driverService.create(driver4);
        driverService.create(driver5);
        carService.addDriverToCar(driver1, car1);
        carService.addDriverToCar(driver2, car1);
        carService.addDriverToCar(driver3, car1);
        carService.addDriverToCar(driver1, car2);
        req.getRequestDispatcher("WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
