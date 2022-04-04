package uz.oak.warehouse.repository;

import org.springframework.stereotype.Repository;
import uz.oak.warehouse.entity.TempHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TempHouseRepository extends JpaRepository<TempHouse, Long> {

    List<TempHouse> findAllByMaterialName(String name);



}
