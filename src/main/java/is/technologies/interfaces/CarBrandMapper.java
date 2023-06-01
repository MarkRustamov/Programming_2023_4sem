package is.technologies.interfaces;

import is.technologies.entities.CarBrand;

import java.util.List;

public interface CarBrandMapper {
    void save(CarBrand entity);

    void deleteById(long id);

    void deleteAll();

    void update(CarBrand entity);

    CarBrand getById(long id);

    List<CarBrand> getAll();
}
