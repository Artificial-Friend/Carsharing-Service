package core.basesyntax.dao.jdbc;

import core.basesyntax.dao.DaoManufacturer;
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
            throw new RuntimeException("ERROR: create() method has failed " + throwables);
        }
        return item;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String query = "SELECT * FROM manufacturers WHERE manufacturer_id = ?";
        Manufacturer manufacturer = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
            if (resultSet.next() && !resultSet.getBoolean("manufacturer_deleted")) {
                manufacturer = parseResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("ERROR: get() method has failed " + throwables);
        }
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturers";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
            List<Manufacturer> list = new ArrayList<>();
            while (resultSet.next()) {
                if (!resultSet.getBoolean("manufacturer_deleted")) {
                    list.add(parseResultSet(resultSet));
                }
            }
            return list;
        } catch (SQLException throwables) {
            throw new RuntimeException("ERROR: getAll() method has failed " + throwables);
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
                throw new RuntimeException("ERROR: update() method has failed " + throwables);
            }
        }
        throw new RuntimeException("ERROR: No " + item + " object to update");
    }

    @Override
    public boolean delete(Long id) {
        if (get(id).isPresent()) {
            String query = "UPDATE manufacturers SET manufacturer_deleted = TRUE "
                    + "WHERE manufacturer_id = ?";
            try (Connection connection = ConnectionUtil.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, id);
                statement.executeUpdate();
                statement.close();
                return get(id).isEmpty();
            } catch (SQLException throwables) {
                throw new RuntimeException("ERROR: delete() method has failed " + throwables);
            }
        }
        throw new RuntimeException("ERROR: Database contains no objects with id " + id);
    }

    private Manufacturer parseResultSet(ResultSet resultSet) throws SQLException {
        long manufacturerId = resultSet.getLong("manufacturer_id");
        String manufacturerName = resultSet.getString("manufacturer_name");
        String manufacturerCountry = resultSet.getString("manufacturer_country");
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerId);
        return manufacturer;

    }
}
