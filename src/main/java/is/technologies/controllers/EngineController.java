package is.technologies.controllers;

import is.technologies.dto.EngineDTO;
import is.technologies.entities.CarModel;
import is.technologies.entities.Engine;
import is.technologies.exceptions.EngineException;
import is.technologies.services.CarModelService;
import is.technologies.services.EngineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engine")
public class EngineController {
    private final EngineService engineService;

    private final CarModelService carModelService;

    public EngineController(EngineService engineService, CarModelService carModelService) {
        this.engineService = engineService;
        this.carModelService = carModelService;
    }

    @PostMapping("/save")
    public Engine save(@RequestBody EngineDTO engineDTO) throws EngineException {
        CarModel carModel = carModelService.getById(engineDTO.getCarModelId());
        Engine engine = new Engine(engineDTO.getName(), engineDTO.getCapacity(), engineDTO.getNumberOfCylinders(), engineDTO.getHeight(), carModel);
        engineService.saveOrUpdate(engine);
        return engine;
    }

    @PostMapping("/update/{id}")
    public Engine update(@PathVariable Long id, @RequestBody EngineDTO engineDTO) throws EngineException {
        Engine engine = engineService.getById(id);
        CarModel carModel = carModelService.getById(engineDTO.getCarModelId());
        engine.setName(engineDTO.getName());
        engine.setCapacity(engineDTO.getCapacity());
        engine.setNumberOfCylinders(engineDTO.getNumberOfCylinders());
        engine.setHeight(engineDTO.getHeight());
        engine.setCarModel(carModel);
        engineService.saveOrUpdate(engine);
        return engine;
    }

    @DeleteMapping("delete/{id}")
    public Engine deleteById(@PathVariable Long id) {
        Engine engine = engineService.getById(id);
        engineService.deleteById(id);
        return engine;
    }

    @GetMapping("/getEngine/{id}")
    public Engine getById(@PathVariable Long id) {
        return engineService.getById(id);
    }

    @GetMapping("/getEngines")
    public List<Engine> getAll() {
        return engineService.getAll();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EngineException.class)
    public String handleException(EngineException exception) {
        return exception.getMessage();
    }
}
