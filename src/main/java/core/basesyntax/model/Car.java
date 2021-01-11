package core.basesyntax.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private static long id;
    private String model;
    private Manufacturer manufacturer;
    private List<Driver> drivers;

    public Car(long id, String model, Manufacturer manufacturer) {
        id++;
        this.model = model;
        this.manufacturer = manufacturer;
        drivers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Car setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public Car setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }
}
