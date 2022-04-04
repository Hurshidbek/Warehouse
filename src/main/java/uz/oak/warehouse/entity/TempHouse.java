package uz.oak.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "temp_house")
public class TempHouse{

    @Id
    private Long warehouseId;

    private String materialName;

    private double qty;

    private double price;


}
