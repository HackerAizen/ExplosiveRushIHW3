package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EquipTypesFileModel {
    @JsonProperty("equipment_type")
    private List<EquipTypeModel> equips;
}
