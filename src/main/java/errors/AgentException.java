package errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentException {
    @JsonProperty("err_type")
    private String type;

    @JsonProperty("err_entity")
    private String entity;

    @JsonProperty("err_field")
    private String field;

    public AgentException(String type, String entity, String field) {
        this.type = type;
        this.entity = entity;
        this.field = field;
    }

    public AgentException(String type, String entity) {
        this.type = type;
        this.entity = entity;
        this.field = "NA";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
