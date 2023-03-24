package errors;

public class JsonValidationException extends AgentException {

    public JsonValidationException(String entity, String field) {
        super("input_error", entity, field);
    }
}
