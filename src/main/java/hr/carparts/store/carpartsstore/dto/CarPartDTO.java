package hr.carparts.store.carpartsstore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPartDTO {
    private String name;
    private String categoryString;
    private BigDecimal price;
    private String description;
}
