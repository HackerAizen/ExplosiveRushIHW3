package agents.cook;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.JsonIO;
import errors.JsonValidationException;
import jade.core.Agent;

import java.io.File;
import java.io.IOException;

public class OperationAgent extends Agent {
    /*
                "oper_id": 82325,
            "oper_proc": 434,
            "oper_card": 518,
            "oper_started": "2023-02-28T10:12:37",
            "oper_ended": "2023-02-28T10:12:49",
            "oper_coocker_id": 15,
            "oper_active": false

     */

    private final String fileName = "operation_types.txt";

    protected void setup() {
        OperationFileModel data = JsonIO.readFromFile(fileName, OperationFileModel.class);
    }
}
