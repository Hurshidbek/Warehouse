package uz.oak.warehouse.payload;

import uz.oak.warehouse.entity.TempHouse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private String productName;
    private double productQty;
    private List<TempHouse> productMaterials;




}