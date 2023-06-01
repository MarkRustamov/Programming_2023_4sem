package is.technologies.repositories.Hibernate;

import is.technologies.Utils.HibernateUtil;
import is.technologies.entities.CarBrand;
import is.technologies.entities.CarModel;
import is.technologies.interfaces.Repository;
import is.technologies.interfaces.RepositoryWithForeignKey;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCarModelRepository implements Repository<CarModel>, RepositoryWithForeignKey<CarModel> {
    public HibernateCarModelRepository() {}

    public CarModel save(CarModel entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(long id) {
        CarModel entity = getById(id);
        if (entity == null) {
            return;
        }

        deleteByEntity(entity);
    }

    public void deleteByEntity(CarModel entity) {
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
            Query query = session.createQuery("delete from CarModel");
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public CarModel update(CarModel entity) {
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

    public CarModel getById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(CarModel.class, id);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CarModel", CarModel.class);
            return query.getResultList();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<CarModel> getAllByVId(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CarModel where carBrand = " + id, CarModel.class);
            return query.getResultList();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
