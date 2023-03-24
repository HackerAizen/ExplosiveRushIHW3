package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductTypeModel {
    @JsonProperty("prod_type_id")
    int id;

    @JsonProperty("prod_type_name")
    String typeName;

    @JsonProperty("prod_is_food")
    boolean isFood;
}
