package is.technologies.interfaces;

import is.technologies.entities.CarModel;

import java.util.List;

public interface CarModelMapper {
    void save(CarModel entity);

    void deleteById(long id);

    void deleteAll();

    void update(CarModel entity);

    CarModel getById(long id);

    List<CarModel> getAll();

    List<CarModel> getAllByVId(long id);
}
