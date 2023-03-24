package agents.cook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OperationFileModel {
    @JsonProperty("operation_types")
    private ArrayList<OperationModel> operations;
}
