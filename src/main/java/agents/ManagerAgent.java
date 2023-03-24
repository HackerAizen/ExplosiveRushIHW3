package agents;

import agents.cook.CookModel;
import agents.cook.CookersFileModel;
import agents.cook.OperationFileModel;
import agents.cook.OperationModel;
import agents.customer.CustomerModel;
import agents.customer.InputCustomersFileModel;
import agents.menu.DishCardFileModel;
import agents.menu.DishCardModel;
import agents.menu.DishModel;
import agents.menu.DishesFileModel;
import agents.warehouse.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import common.JsonIO;
import common.LoggerService;
import errors.AgentException;
import errors.JsonValidationException;
import jade.core.*;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ManagerAgent extends Agent {
    private final Logger logger = LoggerService.createLogger(this.getClass().getName());

    private Map<Integer, DishModel> menu = new HashMap<>();
    private Map<Integer, DishCardModel> dishCards = new HashMap<>();
    private Map<Integer, ProductTypeModel> productTypes = new HashMap<>();
    private Map<Integer, ProductModel> products = new HashMap<>();

    private Map<Integer, EquipTypeModel> equipTypes = new HashMap<>();
    private List<EquipModel> equipment = new ArrayList<>();

    private Map<Integer, CookModel> cookers = new HashMap<>();
    private Map<Integer, OperationModel> operationTypes = new HashMap<>();

    private LinkedList<CustomerModel> customers = new LinkedList<>();

    protected void setup() {
        menu = JsonIO.readFromFile("menu_dishes.txt", DishesFileModel.class)
                .getMenu()
                .stream()
                .collect(Collectors.toMap(DishModel::getDishId, Function.identity()));
        dishCards = JsonIO.readFromFile("dish_cards.txt", DishCardFileModel.class)
                .getDishes()
                .stream()
                .collect(Collectors.toMap(DishCardModel::getCardId, Function.identity()));
        productTypes = JsonIO.readFromFile("product_types.txt", ProductTypesFileModel.class)
                .getProductTypes()
                .stream()
                .collect(Collectors.toMap(ProductTypeModel::getId, Function.identity()));
        products = JsonIO.readFromFile("products.txt", ProductsFileModel.class)
                .getProducts()
                .stream()
                .collect(Collectors.toMap(ProductModel::getId, Function.identity()));

        equipTypes = JsonIO.readFromFile("equipment_type.txt", EquipTypesFileModel.class)
                .getEquips()
                .stream()
                .collect(Collectors.toMap(EquipTypeModel::getEquipTypeId, Function.identity()));
        equipment = JsonIO.readFromFile("equipment.txt", EquipmentFileModel.class)
                .getEquipment();

        cookers = JsonIO.readFromFile("cookers.txt", CookersFileModel.class)
                .getCookers()
                .stream()
                .collect(Collectors.toMap(CookModel::getId, Function.identity()));
        operationTypes = JsonIO.readFromFile("operation_types.txt", OperationFileModel.class)
                .getOperations()
                .stream()
                .collect(Collectors.toMap(OperationModel::getId, Function.identity()));

        customers = new LinkedList<>(JsonIO.readFromFile("visitors_orders.txt", InputCustomersFileModel.class)
                .getCustomers());

        List<AID> equipAgents = this.equipment
                .stream()
                .map(equip -> spawnActor(equip.getEquipName(), "agents.warehouse.EquipAgent", "Equip", new Object[] {}))
                .collect(Collectors.toList());

        List<AID> cookAgents = this.cookers.values()
                .stream()
                .map(cook -> spawnActor(cook.getName(), "agents.cook.CookAgent", "Cooker", new Object[]{cook}))
                .collect(Collectors.toList());
        logger.log(Level.INFO, cookAgents.toString());

        addBehaviour(new AcceptCustomerBehaviour(this));
    }

    private void sendMessage(AID agent, String msg) {
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(agent);
        request.setContent(msg);
        request.addReplyTo(this.getAID());
        this.send(request);
    }

    private Optional<String> sendRequest(AID agent, String msg) {
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(agent);
        request.setContent(msg);
        request.addReplyTo(this.getAID());
        this.send(request);

        // Wait for a response
        MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage response = blockingReceive(template, 5000);
        return Optional.ofNullable(response).map(ACLMessage::getContent);
    }

    private AID spawnActor(String name, String className, String containerName, Object[] args) {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, containerName);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        ContainerController container = runtime.createAgentContainer(profile);
        try {
            AgentController agent = container.createNewAgent(name, className, args);
            agent.start();
            return new AID(name, false);
        } catch (StaleProxyException e) {
            String entity = name + ":" + className;
            logger.log(Level.SEVERE, "unable to start agent " + entity);
            e.printStackTrace();;
            JsonIO.fatalError(new AgentException("spawnActor", entity, e.getMessage()));
        }
        return null;
    }

    private class AcceptCustomerBehaviour extends CyclicBehaviour {
        public AcceptCustomerBehaviour(Agent manager) {
            super(manager);
        }

        public void action() {
            try {
                CustomerModel customer = customers.removeFirst();
                double orderEstimateDuration = customer
                        .getTotalOrders()
                        .stream()
                        .map(shortDishDesc -> menu.get(shortDishDesc.getDishId()))
                        .map(dish -> dishCards.get(dish.getDishCardId()))
                        .map(DishCardModel::getCardTime)
                        .reduce(0.0, Double::sum);
                logger.log(Level.INFO, "orderEstimateDuration for " + customer.getName() + " is " + orderEstimateDuration);
            } catch (NoSuchElementException e) {
                logger.log(Level.INFO, "customer queue is empty");
                block();
            }

        }
    }
}