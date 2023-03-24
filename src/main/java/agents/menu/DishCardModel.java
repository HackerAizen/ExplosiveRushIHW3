package agents.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
class OperProduct {
    @JsonProperty("prod_type_id")
    int productTypeId;

    @JsonProperty("prod_quantity")
    double quantity;
}

@Data
class OperationWithProductsModel {
    @JsonProperty("oper_type_id")
    int operationTypeId;

    @JsonProperty("oper_async_point")
    int operationAsyncPoint;

    @JsonProperty("oper_products")
    List<OperProduct> products;
}

@Data
public class DishCardModel {
    @JsonProperty("card_id")
    int cardId;

    @JsonProperty("dish_name")
    String dishName;

    @JsonProperty("card_descr")
    String cardDescription;

    @JsonProperty("card_time")
    double cardTime;

    @JsonProperty("equip_type")
    int equipType;

    @JsonProperty("operations")
    List<OperationWithProductsModel> operations;
}
