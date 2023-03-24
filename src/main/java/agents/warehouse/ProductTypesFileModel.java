package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductTypesFileModel {
    @JsonProperty("product_types")
    private List<ProductTypeModel> productTypes;
}
