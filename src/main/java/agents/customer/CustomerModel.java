package agents.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerModel {
    @JsonProperty("vis_name")
    private String name;

    @JsonProperty("vis_ord_started")
    private Date createdAt;

    @JsonProperty("vis_ord_ended")
    private Date finishedAt;

    @JsonProperty("vis_ord_total")
    private Integer orderTotal;

    @JsonProperty("vis_ord_dishes")
    private List<OrderDishModel> totalOrders;
}

