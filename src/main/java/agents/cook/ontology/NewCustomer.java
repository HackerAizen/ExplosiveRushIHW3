package agents.cook.ontology;

import agents.customer.CustomerModel;
import jade.content.AgentAction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NewCustomer implements AgentAction {
    private CustomerModel customer;
}
