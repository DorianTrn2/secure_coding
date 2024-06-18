package hr.carparts.store.carpartsstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CAR_PART_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class CarPartCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
}
