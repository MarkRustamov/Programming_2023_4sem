package is.technologies.services;

import is.technologies.entities.CarModel;
import is.technologies.interfaces.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarModelService {
    private final CarModelRepository carModelRepository;

    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public CarModel saveOrUpdate(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public void deleteById(Long id) {
        carModelRepository.deleteById(id);
    }

    public void delete(CarModel carModel) {
        carModelRepository.delete(carModel);
    }

    public CarModel getById(Long id) {
        return carModelRepository.findById(id).orElseThrow();
    }

    public List<CarModel> getAll() {
        return carModelRepository.findAll();
    }

    List<CarModel> getAllByVId(Long id) {
        List<CarModel> carModels = carModelRepository.getAllByVId(id);
        if (carModels == null)
            throw new NoSuchElementException();

        return carModels;
    }

    CarModel getCarModelByEngineId(Long id) {
        CarModel carModel = carModelRepository.getCarModelByEngineId(id);
        if (carModel == null)
            throw new NoSuchElementException();

        return carModel;
    }
}
