package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class ProductModel {
    @JsonProperty("prod_item_id")
    int id;

    @JsonProperty("prod_item_type ")
    int typeId;

    @JsonProperty("prod_item_name")
    String name;

    @JsonProperty("prod_item_company")
    String company;

    @JsonProperty("prod_item_unit")
    String unitName;

    @JsonProperty("prod_item_quantity")
    double quantity;

    @JsonProperty("prod_item_cost")
    int cost;

    @JsonProperty("prod_item_delivered")
    Date deliveredAt;

    @JsonProperty("prod_item_valid_until")
    Date expirationDate;
}
