package is.technologies.interfaces;

import is.technologies.entities.CarModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    @Query(value = "select * from car_model where car_brand = :id", nativeQuery = true)
    List<CarModel> getAllByVId(@Param("id") Long id);

    @Query(value = "select cm.* from car_model as cm join engine as e on cm.id = e.car_model where e.id = :id", nativeQuery = true)
    CarModel getCarModelByEngineId(@Param("id") Long id);
}
