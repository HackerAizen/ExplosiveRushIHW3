package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductsFileModel {
    @JsonProperty("products")
    private List<ProductModel> products;
}
