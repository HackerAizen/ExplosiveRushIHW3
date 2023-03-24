package agents.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DishCardFileModel {
    @JsonProperty("dish_cards")
    private List<DishCardModel> dishes;
}
