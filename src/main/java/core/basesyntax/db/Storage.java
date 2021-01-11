package core.basesyntax.db;

import core.basesyntax.model.Manufacturer;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static Long manufacturerId = 0L;

    public static final Map<Long, Manufacturer> manufacturers = new HashMap<>();

    public static Long generateId() {
        Long val = manufacturerId;
        manufacturerId++;
        return val;
    }
}
