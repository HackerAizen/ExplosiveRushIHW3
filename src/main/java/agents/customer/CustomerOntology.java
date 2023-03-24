package agents.customer;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;

public class CustomerOntology extends Ontology {
    public static final String ONTOLOGY_NAME = "CustomerOntology";
    public static final String NAME = "name";
    public static final String CREATED_AT = "createdAt";
    public static final String FINISHED_AT = "finishedAt";
    public static final String ORDER_TOTAL = "orderTotal";
    public static final String TOTAL_ORDERS = "totalOrders";

    public static final String ORDER_DISH_ONTOLOGY = "OrderDishOntology";
    public static final String ORDER_DISH_ID = "ORDER_DISH_ID";
    public static final String DISH_ID = "DISH_ID";

    private static CustomerOntology instance = new CustomerOntology();

    public static CustomerOntology getInstance() {
        return instance;
    }

    private CustomerOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());

        try {
            add(new ConceptSchema(ONTOLOGY_NAME), CustomerModel.class);
            add(new ConceptSchema(ORDER_DISH_ONTOLOGY), OrderDishModel.class);
            ConceptSchema orderDishSchema = (ConceptSchema) getSchema(ORDER_DISH_ONTOLOGY);
            orderDishSchema.add(ORDER_DISH_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
            orderDishSchema.add(DISH_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));

            ConceptSchema customerSchema = (ConceptSchema) getSchema(ONTOLOGY_NAME);
            customerSchema.add(NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
            customerSchema.add(CREATED_AT, (PrimitiveSchema) getSchema(BasicOntology.DATE));
            customerSchema.add(FINISHED_AT, (PrimitiveSchema) getSchema(BasicOntology.DATE));
            customerSchema.add(ORDER_TOTAL, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
            customerSchema.add(TOTAL_ORDERS, orderDishSchema, 1, ObjectSchema.UNLIMITED);
        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }
}