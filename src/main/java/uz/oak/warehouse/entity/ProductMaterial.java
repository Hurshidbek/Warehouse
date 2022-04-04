package uz.oak.warehouse.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_material")
public class ProductMaterial extends BaseEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Material material;

    private double quantity;





}
