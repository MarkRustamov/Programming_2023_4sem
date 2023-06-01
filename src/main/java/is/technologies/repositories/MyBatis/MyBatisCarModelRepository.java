package is.technologies.repositories.MyBatis;

import is.technologies.Utils.MyBatisUtil;
import is.technologies.entities.CarModel;
import is.technologies.interfaces.CarBrandMapper;
import is.technologies.interfaces.CarModelMapper;
import is.technologies.interfaces.Repository;
import is.technologies.interfaces.RepositoryWithForeignKey;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MyBatisCarModelRepository implements Repository<CarModel>, RepositoryWithForeignKey<CarModel> {
    public MyBatisCarModelRepository() {}

    public CarModel save(CarModel entity) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            mapper.save(entity);
            session.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteByEntity(CarModel entity) {
        deleteById(entity.getId());
    }

    public void deleteAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            mapper.deleteAll();
            session.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarModel update(CarModel entity) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            mapper.update(entity);
            session.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarModel getById(long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            return mapper.getById(id);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            return mapper.getAll();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAllByVId(long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarModelMapper mapper = session.getMapper(CarModelMapper.class);
            return mapper.getAllByVId(id);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
