package core.basesyntax.security;

import core.basesyntax.exception.AuthenticationException;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;

@SuppressWarnings("unused")
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver userFromDB = driverService.findByLogin(login).orElseThrow(
                () -> (new AuthenticationException("Invalid login or password")));
        if (userFromDB.getPassword().equals(password)) {
            return userFromDB;
        }
        throw new AuthenticationException("Invalid login or password");
    }
}
