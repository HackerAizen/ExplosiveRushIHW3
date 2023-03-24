package agents.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DishModel {
    @JsonProperty("menu_dish_id")
    private int dishId;

    @JsonProperty("menu_dish_card")
    private int dishCardId;

    @JsonProperty("menu_dish_price")
    private int price;

    @JsonProperty("menu_dish_active")
    private boolean active;
}

