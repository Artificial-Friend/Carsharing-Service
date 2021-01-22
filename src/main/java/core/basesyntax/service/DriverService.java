package core.basesyntax.service;

import core.basesyntax.exception.AuthenticationException;
import core.basesyntax.model.Driver;
import java.util.Optional;

public interface DriverService extends GenericService<Driver, Long> {
    Optional<Driver> findByLogin(String login) throws AuthenticationException;
}
