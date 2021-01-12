package core.basesyntax.db;

import core.basesyntax.model.Manufacturer;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    public static final Map<Long, Manufacturer> manufacturers = new HashMap<>();
    private static Long manufacturerId = 0L;

    public static void save(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturers.put(manufacturerId, manufacturer);
    }

}
