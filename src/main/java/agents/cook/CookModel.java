package agents.cook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CookModel {
    @JsonProperty("cook_id")
    private Integer id;

    @JsonProperty("cook_name")
    private String name;

    @JsonProperty("cook_active")
    private Boolean active;
}
