package is.technologies.interfaces;

import is.technologies.entities.Engine;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    @Query(value = "select * from engine where car_model = :id", nativeQuery = true)
    List<Engine> getAllByVId(@Param("id") Long id);
}
