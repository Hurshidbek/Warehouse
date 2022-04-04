package uz.oak.warehouse.repository;

import org.springframework.stereotype.Repository;
import uz.oak.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
