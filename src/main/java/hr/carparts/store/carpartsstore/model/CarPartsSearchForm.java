package hr.carparts.store.carpartsstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPartsSearchForm {
    private String name;
    private String category;
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;
    private String description;
}
