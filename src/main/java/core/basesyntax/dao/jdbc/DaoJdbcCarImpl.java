package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.DaoCar;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Car;
import core.basesyntax.model.Driver;
import core.basesyntax.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Dao
public class DaoJdbcCarImpl implements DaoCar {
    @Override
    public Car create(Car item) {
        String query = "INSERT INTO cars (model, manufacturer_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getModel());
            statement.setLong(2, item.getManufacturer().getId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getObject("id", Long.class));
            }
            addDriversIntoCar(item.getId(), item.getDrivers(), connection);
            return item;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't add car " + item, throwables);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT cars.id, model, manufacturer_id, name, country FROM cars "
                + "JOIN manufacturers m on cars.manufacturer_id = m.id "
                + "WHERE cars.deleted = false AND cars.id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Car car = DaoUtils.parseCar(resultSet);
                car.setDrivers(getDrivers(id, connection));
                return Optional.of(car);
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't get car by id " + id, throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT cars.id, model, manufacturer_id, name, country FROM cars "
                + "JOIN manufacturers m on cars.manufacturer_id = m.id "
                + "WHERE cars.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(DaoUtils.parseCar(resultSet));
            }
            for (Car car : cars) {
                car.setDrivers(getDrivers(car.getId(), connection));
            }
            resultSet.close();
            return cars;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't get all cars", throwables);
        }
    }

    @Override
    public Car update(Car item) {
        String query = "UPDATE cars SET model = ?, manufacturer_id = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getModel());
            statement.setLong(2, item.getManufacturer().getId());
            statement.setLong(3, item.getId());
            statement.execute();
            deleteDriversFromCar(item.getId(), connection);
            addDriversIntoCar(item.getId(), item.getDrivers(), connection);
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't update car " + item, throwables);
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't delete car by id " + id, throwables);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT cars.id, cars.model, cars.manufacturer_id, "
                + "m.name, m.country, drivers.deleted FROM cars "
                + "INNER JOIN cars_drivers cs ON cs.car_id = cars.id "
                + "INNER JOIN drivers ON cs.driver_id = drivers.id "
                + "JOIN manufacturers m on cars.manufacturer_id = m.id "
                + "WHERE cars.deleted = false AND drivers.deleted = false AND "
                + "drivers.id = ? ORDER BY cars.id;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(DaoUtils.parseCar(resultSet));
            }
            return cars;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: can't get all cars by driver with id "
                    + driverId, throwables);
        }
    }

    private List<Driver> getDrivers(Long id, Connection connection) throws SQLException {
        String query = "SELECT cars.id, d.id, d.name, d.license_number, "
                + "d.deleted FROM cars INNER JOIN cars_drivers cs ON cs.car_id = cars.id "
                + "    INNER JOIN drivers d ON cs.driver_id = d.id "
                + "WHERE cars.id = ? AND cars.deleted = false AND d.deleted = false "
                + "ORDER BY d.id;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Driver> drivers = new ArrayList<>();
        while (resultSet.next()) {
            drivers.add(DaoUtils.parseDriver(resultSet));
        }
        return drivers;
    }

    private void addDriversIntoCar(Long id, List<Driver> drivers, Connection connection)
            throws SQLException {
        String query = "INSERT INTO cars_drivers (car_id, driver_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        for (Driver driver : drivers) {
            statement.setLong(2, driver.getId());
            statement.execute();
        }
        statement.close();
    }

    private void deleteDriversFromCar(Long id, Connection connection) throws SQLException {
        String query = "DELETE FROM cars_drivers WHERE car_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }
}
