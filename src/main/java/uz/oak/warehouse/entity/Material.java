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
@Table(name = "material")
public class Material extends BaseEntity{

    @Column(nullable = false)
    private String name;

//    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
//    private List<ProductMaterial> productMaterials ;



}
