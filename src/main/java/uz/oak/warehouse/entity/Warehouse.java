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
@Table(name = "warehouse")
public class Warehouse extends BaseEntity{

    @ManyToOne
    private Material material;

    @Column(nullable = false)
    private double remainder;

    @Column(nullable = false)
    private double price;



}
