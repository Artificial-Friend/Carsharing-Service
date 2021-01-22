package core.basesyntax.dao.jdbc;

import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.model.Manufacturer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {
    public static Driver parseDriver(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getObject("name", String.class);
        String login = resultSet.getObject("login", String.class);
        String password = resultSet.getObject("password", String.class);
        String licenseNumber = resultSet.getObject("license_number", String.class);
        Driver driver = new Driver(name, licenseNumber, login, password);
        driver.setId(id);
        return driver;
    }

    public static Manufacturer parseManufacturer(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getObject("name", String.class);
        String country = resultSet.getObject("country", String.class);
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }

    public static Car parseCar(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String model = resultSet.getString("model");
        Long manufacturerID = resultSet.getObject("manufacturer_id", Long.class);
        String manufacturerName = resultSet.getString("name");
        String manufacturerCountry = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerID);
        Car car = new Car(model, manufacturer);
        car.setId(id);
        return car;
    }
}
