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

@Dao
public class DaoJdbcManufacturerImpl implements DaoManufacturer {
    @Override
    public Manufacturer create(Manufacturer item) {
        String query = "INSERT INTO manufacturers (manufacturer_name, manufacturer_country) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setString(2, item.getCountry());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getObject(1, Long.class));
            }
            statement.close();
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
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
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
        String query = "SELECT * FROM manufacturers WHERE manufacturers_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
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
        if (get(item.getId()).isPresent()) {
            String query = "UPDATE manufacturers SET manufacturer_name = ?, "
                    + "manufacturer_country = ? WHERE manufacturer_id = ?";
            try (Connection connection = ConnectionUtil.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, item.getName());
                statement.setString(2, item.getCountry());
                statement.setLong(3, item.getId());
                statement.executeUpdate();
                statement.close();
                return get(item.getId()).get();
            } catch (SQLException throwables) {
                throw new DataProcessingException("ERROR: update() method has failed for "
                        + item, throwables);
            }
        }
        throw new RuntimeException("ERROR: Database contains no " + item + " object to update");
    }

    @Override
    public boolean delete(Long id) {
        if (get(id).isPresent()) {
            String query = "UPDATE manufacturers SET manufacturer_deleted = TRUE "
                    + "WHERE manufacturer_id = ?";
            try (Connection connection = ConnectionUtil.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, id);
                int result = statement.executeUpdate();
                statement.close();
                return result > 0;
            } catch (SQLException throwables) {
                throw new DataProcessingException("ERROR: delete() method has failed for id="
                        + id, throwables);
            }
        }
        throw new RuntimeException("ERROR: Database contains no objects with id=" + id);
    }

    private Manufacturer parseResultSet(ResultSet resultSet) throws SQLException {
        long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String manufacturerName = resultSet.getObject("manufacturer_name", String.class);
        String manufacturerCountry = resultSet.getObject("manufacturer_country", String.class);
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerId);
        return manufacturer;
    }
}
