package is.technologies.interfaces;

import is.technologies.entities.CarBrand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarBrandRepositoryTest {
    @Autowired
    private CarBrandRepository carBrandRepository;

    @Test
    void saveMethod() {
        Date date = new Date(123455678911L);
        CarBrand carBrand = new CarBrand("Pontiac", date);
        CarBrand savedCarBrand = carBrandRepository.save(carBrand);
        assertEquals(carBrand.getName(), savedCarBrand.getName());
        //assertEquals(carBrand.getFoundingDate(), savedCarBrand.getFoundingDate());
    }

    @Test
    void updateMethod() {
        CarBrand carBrand = new CarBrand("Skoda", new Date(423455678918L));
        CarBrand savedCarBrand = carBrandRepository.save(carBrand);
        savedCarBrand.setName("Citroen");
        CarBrand updatedCarBrand = carBrandRepository.save(savedCarBrand);
        assertEquals(savedCarBrand.getName(), updatedCarBrand.getName());
    }

    @Test
    void deleteMethod() {
        CarBrand carBrand = new CarBrand("Skoda", new Date(12345567891L));
        CarBrand savedCarBrand = carBrandRepository.save(carBrand);
        assertDoesNotThrow(() -> { carBrandRepository.delete(savedCarBrand); });
    }

    @Test
    void deleteAllMethod() {
        assertDoesNotThrow(() -> { carBrandRepository.deleteAll(); });
    }

    @Test
    void getAllMethod() {
        List<CarBrand> carBrands = carBrandRepository.findAll();
        for (CarBrand carBrand : carBrands)
            System.out.println(carBrand.getId() + " " + carBrand.getName() + " " + carBrand.getFoundingDate());
    }

    @Test
    void getCarModelByIdMethod() {
        CarBrand carBrand = carBrandRepository.getCarBrandByCarModelId(10L);
        System.out.println(carBrand.getId() + " " + carBrand.getName() + " " + carBrand.getFoundingDate());
    }
}