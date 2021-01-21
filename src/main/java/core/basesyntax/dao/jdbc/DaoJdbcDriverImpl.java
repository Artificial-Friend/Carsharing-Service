package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.DaoDriver;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.lib.Dao;
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
public class DaoJdbcDriverImpl implements DaoDriver {
    @Override
    public Driver create(Driver item) {
        String query = "INSERT INTO drivers (name, license_number) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getLicenseNumber());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getObject("id", Long.class));
            }
            return item;
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't insert object " + item, throwables);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT id, name, license_number FROM drivers "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(DaoUtils.parseDriver(resultSet));
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get driver by id=" + id, throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT id, name, license_number FROM drivers WHERE deleted = false "
                + "ORDER BY id";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(DaoUtils.parseDriver(resultSet));
            }
            return drivers;
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get all drivers", throwables);
        }
    }

    @Override
    public Driver update(Driver item) {
        String query = "UPDATE drivers SET name = ?, license_number = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getLicenseNumber());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to update driver for " + item,
                    throwables);
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = true "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to delete", throwables);
        }
    }
}
