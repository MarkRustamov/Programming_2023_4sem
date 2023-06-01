package is.technologies.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import is.technologies.dto.CarModelDTO;
import is.technologies.entities.CarBrand;
import is.technologies.entities.CarModel;
import is.technologies.exceptions.CarModelException;
import is.technologies.services.CarBrandService;
import is.technologies.services.CarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carModel")
public class CarModelController {
    private final CarModelService carModelService;

    private final CarBrandService carBrandService;

    public CarModelController(CarModelService carModelService, CarBrandService carBrandService) {
        this.carModelService = carModelService;
        this.carBrandService = carBrandService;
    }

    @PostMapping("/save")
    public CarModel save(@RequestBody CarModelDTO carModelDTO) throws CarModelException {
        CarBrand carBrand = carBrandService.getById(carModelDTO.getCarBrandId());
        CarModel carModel = new CarModel(carModelDTO.getName(), carModelDTO.getLength(), carModelDTO.getWidth(), carModelDTO.getBodyType(), carBrand, carModelDTO.getHeight());
        carModelService.saveOrUpdate(carModel);
        return carModel;
    }

    @PostMapping("/update/{id}")
    public CarModel update(@PathVariable Long id, @RequestBody CarModelDTO carModelDTO) throws CarModelException {
        CarModel carModel = carModelService.getById(id);
        CarBrand carBrand = carBrandService.getById(carModelDTO.getCarBrandId());
        carModel.setName(carModelDTO.getName());
        carModel.setLength(carModel.getLength());
        carModel.setWidth(carModelDTO.getWidth());
        carModel.setBodyType(carModelDTO.getBodyType());
        carModel.setCarBrand(carBrand);
        carModel.setHeight(carModel.getHeight());
        carModelService.saveOrUpdate(carModel);
        return carModel;
    }

    @DeleteMapping("delete/{id}")
    public CarModel deleteById(@PathVariable Long id) {
        CarModel carModel = carModelService.getById(id);
        carModelService.deleteById(id);
        return carModel;
    }

    @GetMapping("/getCarModel/{id}")
    public CarModel getById(@PathVariable Long id) {
        return carModelService.getById(id);
    }

    @GetMapping("/getCarModels")
    public List<CarModel> getAll() {
        return carModelService.getAll();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CarModelException.class)
    public String handleException(CarModelException exception) {
        return exception.getMessage();
    }
}
