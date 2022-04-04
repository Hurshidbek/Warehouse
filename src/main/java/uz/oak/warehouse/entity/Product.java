package uz.oak.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "product")
public class Product extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "code", unique = true)
    private Long code;
//
//    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
//    private List<ProductMaterial> productMaterials ;


}
