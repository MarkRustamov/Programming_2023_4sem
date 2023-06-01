package is.technologies.interfaces;

import is.technologies.entities.CarBrand;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    @Query(value = "select cb.* from car_brand as cb join car_model as cm on cb.id = cm.car_brand where cm.id = :id", nativeQuery = true)
    CarBrand getCarBrandByCarModelId(@Param("id") Long id);
}
