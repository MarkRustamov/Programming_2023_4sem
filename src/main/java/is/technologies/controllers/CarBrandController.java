package is.technologies.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import is.technologies.dto.CarBrandDTO;
import is.technologies.entities.CarBrand;
import is.technologies.services.CarBrandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/carBrand")
public class CarBrandController {
    private final CarBrandService carBrandService;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @PostMapping("/save")
    public CarBrand save(@RequestBody CarBrandDTO carBrandDTO) {
        CarBrand carBrand = new CarBrand(carBrandDTO.getName(), carBrandDTO.getFoundingDate());
        carBrandService.saveOrUpdate(carBrand);
        return carBrand;
    }

    @PostMapping("/update/{id}")
    public CarBrand update(@PathVariable Long id, @RequestBody CarBrandDTO carBrandDTO) {
        CarBrand carBrand = carBrandService.getById(id);
        carBrand.setName(carBrandDTO.getName());
        carBrand.setFoundingDate(carBrandDTO.getFoundingDate());
        carBrandService.saveOrUpdate(carBrand);
        return carBrand;
    }

    @DeleteMapping("delete/{id}")
    public CarBrand deleteById(@PathVariable Long id) {
        CarBrand carBrand = carBrandService.getById(id);
        carBrandService.deleteById(id);
        return carBrand;
    }

    @GetMapping("/getCarBrand/{id}")
    public CarBrand getById(@PathVariable Long id) {
        return carBrandService.getById(id);
    }
}
