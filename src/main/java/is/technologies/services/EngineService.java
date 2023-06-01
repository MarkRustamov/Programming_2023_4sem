package is.technologies.services;

import is.technologies.entities.Engine;
import is.technologies.interfaces.EngineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EngineService {
    private final EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public Engine saveOrUpdate(Engine engine) {
        return engineRepository.save(engine);
    }

    public void deleteById(Long id) {
        engineRepository.deleteById(id);
    }

    public void delete(Engine engine) {
        engineRepository.delete(engine);
    }

    public Engine getById(Long id) {
        return engineRepository.findById(id).orElseThrow();
    }

    public List<Engine> getAll() {
        return engineRepository.findAll();
    }

    List<Engine> getAllByVId(Long id) {
        List<Engine> engines = engineRepository.getAllByVId(id);
        if (engines == null)
            throw new NoSuchElementException();

        return engines;
    }
}
