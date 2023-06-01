package is.technologies.repositories.Hibernate;

import is.technologies.Utils.HibernateUtil;
import is.technologies.entities.CarBrand;
import is.technologies.interfaces.Repository;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCarBrandRepository implements Repository<CarBrand> {
    public HibernateCarBrandRepository() {}

    public CarBrand save(CarBrand entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(long id) {
        CarBrand entity = getById(id);
        if (entity == null) {
            return;
        }

        deleteByEntity(entity);
    }

    public void deleteByEntity(CarBrand entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from CarBrand");
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand update(CarBrand entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarBrand getById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(CarBrand.class, id);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarBrand> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CarBrand", CarBrand.class);
            return query.getResultList();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
