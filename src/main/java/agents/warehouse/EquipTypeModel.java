package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EquipTypeModel {
    @JsonProperty("equip_type_id")
    int equipTypeId;

    @JsonProperty("equip_type_name")
    String equipTypeName;
}
