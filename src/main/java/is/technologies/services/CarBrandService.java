package is.technologies.services;

import is.technologies.entities.CarBrand;
import is.technologies.interfaces.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public CarBrand saveOrUpdate(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }

    public void deleteById(Long id) {
        carBrandRepository.deleteById(id);
    }

    public void delete(CarBrand carBrand) {
        carBrandRepository.delete(carBrand);
    }

    public CarBrand getById(Long id) {
        return carBrandRepository.findById(id).orElseThrow();
    }

    public List<CarBrand> getAll() {
        return carBrandRepository.findAll();
    }

    public CarBrand getCarBrandByCarModelId(Long id) {
        CarBrand carBrand = carBrandRepository.getCarBrandByCarModelId(id);
        if (carBrand == null)
            throw new NoSuchElementException();

        return carBrand;
    }
}
