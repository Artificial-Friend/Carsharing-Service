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
        String query = "INSERT INTO manufacturers (manufacturer_name, manufacturer_country) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getCountry());
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getObject("manufacturer_id", Long.class));
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: create() method has failed for "
                    + item, throwables);
        }
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String query = "SELECT * FROM manufacturers WHERE manufacturer_id = ? AND "
                + "manufacturer_deleted = false";
        Manufacturer manufacturer = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                manufacturer = parseResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: get() method has failed for id="
                    + id, throwables);
        }
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturers WHERE manufacturer_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            List<Manufacturer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(parseResultSet(resultSet));
            }
            return list;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: getAll() method has failed", throwables);
        }
    }

    @Override
    public Manufacturer update(Manufacturer item) {
        String query = "UPDATE manufacturers SET manufacturer_name = ?, "
                + "manufacturer_country = ? WHERE manufacturer_id = ? "
                + "AND manufacturer_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getCountry());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: update() method has failed for "
                    + item, throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacturers SET manufacturer_deleted = TRUE "
                + "WHERE manufacturer_id = ? AND manufacturer_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DataProcessingException("ERROR: delete() method has failed for id="
                    + id, throwables);
        }
    }

    private Manufacturer parseResultSet(ResultSet resultSet) throws SQLException {
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String manufacturerName = resultSet.getObject("manufacturer_name", String.class);
        String manufacturerCountry = resultSet.getObject("manufacturer_country", String.class);
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerId);
        return manufacturer;
    }
}
