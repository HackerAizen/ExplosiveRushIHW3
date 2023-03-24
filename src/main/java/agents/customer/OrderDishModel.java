package agents.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDishModel {
    @JsonProperty("ord_dish_id")
    private Integer orderDishId;

    @JsonProperty("menu_dish")
    private Integer dishId;
}
