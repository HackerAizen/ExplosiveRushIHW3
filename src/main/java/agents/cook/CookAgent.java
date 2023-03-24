package agents.cook;

import agents.customer.CustomerModel;
import agents.warehouse.EquipModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.JsonIO;
import common.LoggerService;
import errors.AgentException;
import errors.JsonValidationException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

public class CookAgent extends Agent {
    private Logger logger;

    private Integer id;
    private String name;
    private Boolean active;

    private Optional<EquipModel> equip = Optional.empty();
//    private Optional<OrderAgent> wip = Optional.empty();
//    private LinkedList<OperationModel>

    private ObjectMapper objectMapper = new ObjectMapper();
    private int busyState = new Random().nextInt();

    protected void setup() {
        Object[] args = getArguments();
        if (args == null || args.length != 1) {
            System.err.println("invalid CookAgent args");
            JsonIO.fatalError(new AgentException("invalid_args", "CookAgent"));
        }

        assert args != null;
        if (args[0] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "CookAgent", "model"));
        }
        CookModel model = (CookModel) args[0];

        id = model.getId();
        name = model.getName();
        active = model.getActive();

        logger = LoggerService.createLogger(name);

        addBehaviour(new AcceptCommandsBehaviour(this));
    }

    private Integer queryBusyState() {
        // busy for the next 2 hours = return 1000 * 60 * 60 * 2
        return busyState;
    }

    private String queryCompletionState() {
        return name + " will free in 5 minutes";
    }

    private class AcceptCommandsBehaviour extends CyclicBehaviour {
        public AcceptCommandsBehaviour(Agent cook) {
            super(cook);
        }
        public void action() {
            ACLMessage msg = myAgent.receive();

            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    String body = msg.getContent();
                    System.out.println(getAID() + " " + body);
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);

                    switch (body) {
                        case "queryBusyState":
                            reply.setContent(queryCompletionState());
                            send(reply);
                            break;
                        case "newCustomer":
                            try {
                                CustomerModel customer = objectMapper.readValue(body, CustomerModel.class);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                                JsonIO.fatalError(new JsonValidationException("CookEntity_newCustomer", e.getLocation().offsetDescription()));
                            }
                        default:
                            JsonIO.fatalError(new AgentException("invalid_command", "CookAgent", body));
                    }

                }
            } else {
                block();
            }
        }
    }
}