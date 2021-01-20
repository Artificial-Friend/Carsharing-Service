package core.basesyntax.controller.car;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Car;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.CarService;
import core.basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService
            = (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/car/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String model = req.getParameter("model");
        String manufacturerName = req.getParameter("manufacturer");
        Manufacturer manufacturer = null;
        for (Manufacturer mf : manufacturerService.getAll()) {
            if (mf.getName().equals(manufacturerName)) {
                manufacturer = mf;
            }
        }
        if (manufacturer == null) {
            manufacturer = manufacturerService.create(new Manufacturer(manufacturerName,
                    "front-end now can redirect user to manufacturerService.update() method"));
        }
        carService.create(new Car(model, manufacturer));
    }
}
