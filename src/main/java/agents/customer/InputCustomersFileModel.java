package agents.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InputCustomersFileModel {
    @JsonProperty("visitors_orders")
    private List<CustomerModel> customers;
}
