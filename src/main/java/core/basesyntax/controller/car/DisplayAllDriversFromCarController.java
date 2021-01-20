package core.basesyntax.controller.car;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Driver;
import core.basesyntax.service.CarService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayAllDriversFromCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final CarService carService
            = (CarService) injector.getInstance(CarService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        List<Driver> allDrivers = carService.get(id).getDrivers();
        req.setAttribute("drivers", allDrivers);
        req.getRequestDispatcher("/WEB-INF/views/driver/all.jsp").forward(req, resp);
    }
}
