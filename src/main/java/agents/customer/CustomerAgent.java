package agents.customer;

import common.JsonIO;
import errors.AgentException;
import jade.core.Agent;

public class CustomerAgent extends Agent {
    private CustomerModel customerModel;

    protected void setup() {
        Object[] args = getArguments();
        if (args == null || args.length != 1) {
            System.err.println("invalid CustomerAgent args");
            JsonIO.fatalError(new AgentException("invalid_args", "CustomerAgent"));
        }

        assert args != null;
        if (args[0] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "CustomerAgent", "model"));
        }
        customerModel = (CustomerModel) args[0];

        System.out.println("CustomerAgent " + customerModel.getName() + " created");
    }
}