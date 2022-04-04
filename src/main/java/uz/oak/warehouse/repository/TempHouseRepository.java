package uz.oak.warehouse.repository;

import uz.oak.warehouse.entity.TempHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempHouseRepository extends JpaRepository<TempHouse, Long> {

    List<TempHouse> findAllByMaterialName(String name);


}
