package agents.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EquipModel {
    @JsonProperty("equip_type")
    private int equipTypeId;

    @JsonProperty("equip_name")
    private String equipName;

    @JsonProperty("equip_active")
    private boolean active;
}
