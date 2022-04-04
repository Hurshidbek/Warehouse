package uz.oak.warehouse.repository;

import org.springframework.stereotype.Repository;
import uz.oak.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {


}
