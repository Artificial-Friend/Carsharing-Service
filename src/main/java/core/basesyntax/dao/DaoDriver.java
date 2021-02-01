package core.basesyntax.dao;

import core.basesyntax.model.Driver;
import java.util.Optional;

public interface DaoDriver extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
