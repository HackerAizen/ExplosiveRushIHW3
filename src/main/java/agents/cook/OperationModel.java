package agents.cook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OperationModel {
    @JsonProperty("oper_type_id")
    private Integer id;

    @JsonProperty("oper_type_name")
    private String operationName;
}

