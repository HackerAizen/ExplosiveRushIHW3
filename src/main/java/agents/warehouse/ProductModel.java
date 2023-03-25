package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class ProductModel {
    @JsonProperty("prod_item_id")
    private int id;

    @JsonProperty("prod_item_type ")
    private int typeId;

    @JsonProperty("prod_item_name")
    private String name;

    @JsonProperty("prod_item_company")
    private String company;

    @JsonProperty("prod_item_unit")
    private String unitName;

    @JsonProperty("prod_item_quantity")
    private double quantity;

    @JsonProperty("prod_item_cost")
    private int cost;

    @JsonProperty("prod_item_delivered")
    private Date deliveredAt;

    @JsonProperty("prod_item_valid_until")
    private Date expirationDate;
}
