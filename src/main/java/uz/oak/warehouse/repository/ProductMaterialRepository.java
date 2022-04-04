package uz.oak.warehouse.repository;

import org.springframework.stereotype.Repository;
import uz.oak.warehouse.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {

    List<ProductMaterial> findAllByProductId(Long id);

}
