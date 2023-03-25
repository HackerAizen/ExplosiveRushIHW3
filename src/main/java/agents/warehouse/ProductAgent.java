package agents.warehouse;

import common.JsonIO;
import errors.AgentException;
import jade.core.AID;
import jade.core.Agent;

import java.util.Optional;

public class ProductAgent extends Agent {
    private ProductModel productModel;

    // shows who are using product right now (which CookAgent)
    private Optional<AID> inUse = Optional.empty();

    protected void setup() {
        Object[] args = getArguments();
        if (args == null || args.length != 1) {
            System.err.println("invalid ProductAgent args");
            JsonIO.fatalError(new AgentException("invalid_args", "ProductAgent"));
        }

        assert args != null;
        if (args[0] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "ProductAgent", "model"));
        }
        productModel = (ProductModel) args[0];

        System.out.println("ProductAgent " + productModel.getName() + " created");
    }
}
