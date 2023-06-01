package is.technologies.interfaces;

import is.technologies.entities.BodyType;
import is.technologies.entities.CarBrand;
import is.technologies.entities.CarModel;
import is.technologies.exceptions.CarModelException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarModelRepositoryTest {
    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Test
    void saveMethod() throws CarModelException {
        Date date = new Date(523455848911L);
        CarBrand carBrand = new CarBrand("Volkswagen", date);
        CarBrand savedCarBrand = carBrandRepository.save(carBrand);
        CarModel carModel = new CarModel("Amarok", 5254, 1944, BodyType.PICKUP, savedCarBrand, 1834);
        CarModel savedCarModel = carModelRepository.save(carModel);
        assertEquals(carModel.getName(), savedCarModel.getName());
        assertEquals(carModel.getBodyType(), savedCarModel.getBodyType());
    }

    @Test
    void getAllByVIdMethod() {
        List<CarModel> carModels = carModelRepository.getAllByVId(3041L);
        for (CarModel carModel : carModels)
            System.out.println(carModel.getId() + " " + carModel.getName() + " " + carModel.getLength() + " " + carModel.getCarBrand().getId());
    }
}