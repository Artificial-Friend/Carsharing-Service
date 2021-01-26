package core.basesyntax.web.filter;

import core.basesyntax.lib.Injector;
import core.basesyntax.service.DriverService;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final String DRIVER_ID = "id";
    private static final Injector injector = Injector.getInstance("core.basesyntax");
    private final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);
    private final Set<String> allowedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        allowedUrls.add("/login");
        allowedUrls.add("/drivers/add");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (allowedUrls.contains(url)) {
            chain.doFilter(req, resp);
            return;
        }
        Long id = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (id == null) {
            req.setAttribute("errorMsg", "Access denied! Log in or register to get access");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        try {
            driverService.get(id);
        } catch (NoSuchElementException e) {
            req.setAttribute("errorMsg", "Access denied! Log in or register to get access");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
