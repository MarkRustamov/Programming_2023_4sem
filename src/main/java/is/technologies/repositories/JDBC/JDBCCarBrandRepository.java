package is.technologies.repositories.JDBC;

import is.technologies.Utils.DataSource;
import is.technologies.entities.CarBrand;
import is.technologies.exceptions.EntityDoesNotExistException;
import is.technologies.interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarBrandRepository implements Repository<CarBrand> {
    public JDBCCarBrandRepository() {}

    public CarBrand save(CarBrand entity) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into car_brand(name, date) values(?, ?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getFoundingDate());
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet maxId = statement.executeQuery("select last_insert_id() from car_brand");
            maxId.next();
            long id = maxId.getLong(1);

            preparedStatement.close();
            statement.close();
            return new CarBrand(id, entity.getName(), entity.getFoundingDate());
        }
        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            int changes = statement.executeUpdate("delete from car_brand where id = " + id);
            statement.close();

            if (changes == 0)
                throw new EntityDoesNotExistException("There is no entity with id = " + id + " in car_brand");
        }
        catch (SQLException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteByEntity(CarBrand entity) {
        deleteById(entity.getId());
    }

    public void deleteAll() {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("delete from car_brand");
            statement.close();
        }
        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand update(CarBrand entity) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update car_brand set name = ?, date = ? where id = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, entity.getFoundingDate());
            preparedStatement.setLong(3, entity.getId());
            int changes = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (changes == 0) {
                throw new EntityDoesNotExistException("There is no entity with id = " + entity.getId() + " in car_brand");
            }

            return entity;
        }
        catch (SQLException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand getById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from car_brand where id = " + id);

            if (!resultSet.next())
                throw new EntityDoesNotExistException("There is no entity with id = " + id);

            return new CarBrand(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDate("date"));
        }
        catch (SQLException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarBrand> getAll() {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from car_brand");

            var entities = new ArrayList<CarBrand>();
            while (resultSet.next()) {
                var entity = new CarBrand(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDate("date"));
                entities.add(entity);
            }

            statement.close();
            resultSet.close();
            return entities;

        }
        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
