package is.technologies.repositories.MyBatis;

import is.technologies.Utils.MyBatisUtil;
import is.technologies.entities.CarBrand;
import is.technologies.interfaces.CarBrandMapper;
import is.technologies.interfaces.Repository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MyBatisCarBrandRepository implements Repository<CarBrand> {
    public MyBatisCarBrandRepository() {}
    public CarBrand save(CarBrand entity) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
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
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
            mapper.deleteById(id);
            session.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteByEntity(CarBrand entity) {
        deleteById(entity.getId());
    }

    public void deleteAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
            mapper.deleteAll();
            session.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand update(CarBrand entity) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
            mapper.update(entity);
            session.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand getById(long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
            return mapper.getById(id);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarBrand> getAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession()) {
            CarBrandMapper mapper = session.getMapper(CarBrandMapper.class);
            return mapper.getAll();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
