package agents.warehouse;

import agents.customer.CustomerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import common.JsonIO;
import errors.AgentException;
import errors.JsonValidationException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class WarehouseAgent extends Agent {
    private Map<String, EquipModel> equipment;
    private Set<EquipModel> equipmentLocks = new HashSet<>();

    private Map<Integer, ProductModel> products;
    private Set<ProductModel> productsLocks = new HashSet<>();

    protected void setup() {
        Object[] args = getArguments();
        if (args == null || args.length != 1) {
            System.err.println("invalid WarehouseAgent args");
            JsonIO.fatalError(new AgentException("invalid_args", "WarehouseAgent"));
        }

        assert args != null;
        if (args[0] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "WarehouseAgent", "equipment"));
        }
        if (args[1] == null) {
            JsonIO.fatalError(new AgentException("missing_args", "WarehouseAgent", "products"));
        }

        equipment = ((EquipmentFileModel) args[0])
                .getEquipment()
                .stream()
                .collect(Collectors.toMap(EquipModel::getEquipName, Function.identity()));
        products = ((ProductsFileModel) args[1])
                .getProducts()
                .stream()
                .collect(Collectors.toMap(ProductModel::getId, Function.identity()));

        addBehaviour(new AcceptCommandsBehaviour(this));
    }

    private class AcceptCommandsBehaviour extends CyclicBehaviour {

        public AcceptCommandsBehaviour(Agent warehouse) {
            super(warehouse);
        }

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive();

            if (msg != null) {
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    String[] body = msg.getContent().split(";");
//                    System.out.println(getAID() + " " + body);
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);

                    switch (body[0]) {
                        case "acquireEquipment":
                            String equipmentName = body[1];
                            if (equipmentLocks.add(equipment.get(equipmentName))) {
                                reply.setContent("true");
                            } else {
                                reply.setContent("false");
                            }
                            send(reply);
                            break;
                        case "acquireProduct":
                            Integer productId = Integer.parseInt(body[1]);
                            if (productsLocks.add(products.get(productId))) {
                                reply.setContent("true");
                            } else {
                                reply.setContent("false");
                            }
                            send(reply);
                            break;
                        default:
                            JsonIO.fatalError(new AgentException("invalid_command", "WarehouseAgent", msg.getContent()));
                    }

                }
            } else {
                block();
            }
        }
    }
}