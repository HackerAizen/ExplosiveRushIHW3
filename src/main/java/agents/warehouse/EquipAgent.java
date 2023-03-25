package agents.warehouse;

import agents.cook.CookModel;
import common.JsonIO;
import errors.AgentException;
import jade.core.AID;
import jade.core.Agent;

import java.util.Optional;

public class EquipAgent extends Agent {
    private EquipModel equipModel;

    // shows who are using equip right now (which CookAgent)
    private Optional<AID> inUse = Optional.empty();

    protected void setup() {
        Object[] args = getArguments();
        if (args == null || args.length != 1) {
            System.err.println("invalid EquipAgent args");
            JsonIO.fatalError(new AgentException("invalid_args", "EquipAgent"));
        }

        assert args != null;
        if (args[0] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "EquipAgent", "model"));
        }
        equipModel = (EquipModel) args[0];

        System.out.println("EquipAgent " + equipModel.getEquipName() + " created");
    }
}