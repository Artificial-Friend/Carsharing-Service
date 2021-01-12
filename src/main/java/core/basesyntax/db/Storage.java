package core.basesyntax.db;

import core.basesyntax.model.Manufacturer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static final Map<Long, Manufacturer> manufacturers = new HashMap<>();

    private static Long manufacturerId = 0L;

    public static void save(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturers.put(manufacturerId, manufacturer);
    }

    public static Manufacturer get(Long id) {
        return Storage.manufacturers.get(id);
    }

    public static List<Manufacturer> getAll() {
        return new ArrayList<>(Storage.manufacturers.values());
    }

    public static void remove(Long id) {
        Storage.manufacturers.remove(id);
    }

    public static Manufacturer replace(Manufacturer manufacturer) {
        Long key = manufacturer.getId();
        Manufacturer oldVal = Storage.get(key);
        manufacturers.replace(key, manufacturer);
        return oldVal;
    }

}
