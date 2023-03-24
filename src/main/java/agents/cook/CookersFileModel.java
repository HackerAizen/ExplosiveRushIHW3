package agents.cook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CookersFileModel {
    @JsonProperty("cookers")
    private List<CookModel> cookers;
}
