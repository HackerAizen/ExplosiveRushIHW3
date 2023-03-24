package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EquipmentFileModel {
    @JsonProperty("equipment")
    private List<EquipModel> equipment;
}
