package agents.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DishesFileModel {
    @JsonProperty("menu_dishes")
    private List<DishModel> menu;
}
