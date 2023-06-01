package is.technologies.repositories.JDBC;

import is.technologies.Utils.DataSource;
import is.technologies.entities.BodyType;
import is.technologies.entities.CarBrand;
import is.technologies.entities.CarModel;
import is.technologies.exceptions.CarModelException;
import is.technologies.exceptions.EntityDoesNotExistException;
import is.technologies.interfaces.Repository;
import is.technologies.interfaces.RepositoryWithForeignKey;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarModelRepository implements Repository<CarModel>, RepositoryWithForeignKey<CarModel> {
    public JDBCCarModelRepository() {}

    public CarModel save(CarModel entity) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into car_model(name, length, width, body_type, car_brand, height) values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getLength());
            preparedStatement.setInt(3, entity.getWidth());
            preparedStatement.setString(4, entity.getBodyType().toString());
            preparedStatement.setLong(5, entity.getCarBrand().getId());
            preparedStatement.setInt(6, entity.getHeight());
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet maxId = statement.executeQuery("select last_insert_id() from car_model");
            maxId.next();
            long id = maxId.getLong(1);

            preparedStatement.close();
            statement.close();
            return new CarModel(id, entity.getName(), entity.getLength(), entity.getWidth(), entity.getBodyType(), entity.getCarBrand(), entity.getHeight());
        }
        catch (SQLException | CarModelException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            int changes = statement.executeUpdate("delete from car_model where id = " + id);
            statement.close();

            if (changes == 0)
                throw new EntityDoesNotExistException("There is no entity with id = " + id + " in car_model");
        }
        catch (SQLException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteByEntity(CarModel entity) {
        deleteById(entity.getId());
    }

    public void deleteAll() {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("delete from car_model");
            statement.close();
        }
        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarModel update(CarModel entity) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update car_model set name = ?, length = ?, width = ?, body_type = ?, car_brand = ?, height = ? where id = ?;");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getLength());
            preparedStatement.setInt(3, entity.getWidth());
            preparedStatement.setString(4, entity.getBodyType().toString());
            preparedStatement.setLong(5, entity.getCarBrand().getId());
            preparedStatement.setInt(6, entity.getHeight());
            preparedStatement.setLong(7, entity.getId());
            int changes = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (changes == 0) {
                throw new EntityDoesNotExistException("There is no entity with id = " + entity.getId() + " in car_model");
            }

            return entity;
        }
        catch (SQLException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarModel getById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from car_model where id = " + id);

            if (!resultSet.next())
                throw new EntityDoesNotExistException("There is no entity with id = " + id + " in car_model");

            CarBrand brand = getCarBrandById(resultSet.getLong("car_brand"));

            return new CarModel(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("length"), resultSet.getInt("width"),
                    BodyType.valueOf(resultSet.getString("body_type")), brand, resultSet.getInt("height"));
        }
        catch (SQLException | EntityDoesNotExistException | CarModelException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAll() {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from car_model");

            return getCarModels(statement, resultSet);

        }
        catch (SQLException | CarModelException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAllByVId(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from car_model where car_brand = " + id);

            return getCarModels(statement, resultSet);

        }
        catch (SQLException | CarModelException | EntityDoesNotExistException exception) {
            throw new RuntimeException(exception);
        }
    }

    @NotNull
    private List<CarModel> getCarModels(Statement statement, ResultSet resultSet) throws SQLException, CarModelException, EntityDoesNotExistException {
        var entities = new ArrayList<CarModel>();
        while (resultSet.next()) {
            var entity = new CarModel(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("length"), resultSet.getInt("width"),
                    BodyType.valueOf(resultSet.getString("body_type")), getCarBrandById(resultSet.getLong("car_brand")), resultSet.getInt("height"));
            entities.add(entity);
        }

        if (entities.isEmpty()) {
            throw new EntityDoesNotExistException("There is no entities with such foreign id in car_model");
        }

        statement.close();
        resultSet.close();
        return entities;
    }

    @NotNull
    private CarBrand getCarBrandById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet carBrandResultSet = statement.executeQuery("select * from car_brand where id = " + id);
            carBrandResultSet.next();
            return new CarBrand(carBrandResultSet.getLong("id"), carBrandResultSet.getString("name"), carBrandResultSet.getDate("date"));
        }
        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
