package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EquipModel {
    @JsonProperty("equip_type")
    int equipTypeId;

    @JsonProperty("equip_name")
    String equipName;

    @JsonProperty("equip_active")
    boolean active;
}
