package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.DaoManufacturer;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
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
public class DaoJdbcManufacturerImpl implements DaoManufacturer {
    @Override
    public Manufacturer create(Manufacturer item) {
        String query = "INSERT INTO manufacturers (name, country) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getCountry());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getObject("id", Long.class));
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to create manufacturer for "
                    + item, throwables);
        }
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String query = "SELECT id, name, country FROM manufacturers WHERE id = ? AND "
                + "deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(DaoUtils.parseManufacturer(resultSet));
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to get manufacturer by id = "
                    + id, throwables);
        }
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT id, name, country FROM manufacturers WHERE deleted = false "
                + "ORDER BY id";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            List<Manufacturer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(DaoUtils.parseManufacturer(resultSet));
            }
            return list;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to get all manufacturers",
                    throwables);
        }
    }

    @Override
    public Manufacturer update(Manufacturer item) {
        String query = "UPDATE manufacturers SET name = ?, country = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getCountry());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed to update manufacturers table with "
                    + item, throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacturers SET deleted = TRUE "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: failed delete manufacturer with id="
                    + id, throwables);
        }
    }
}
