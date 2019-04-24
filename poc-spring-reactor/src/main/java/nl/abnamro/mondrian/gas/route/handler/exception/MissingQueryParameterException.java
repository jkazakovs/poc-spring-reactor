package nl.abnamro.mondrian.gas.route.handler.exception;

public class MissingQueryParameterException extends RuntimeException {

    private String queryParameterName;

    public MissingQueryParameterException(String queryParameterName) {
        this.queryParameterName = queryParameterName;
    }

    public String getErrorMessageAsJson() {
        return "{\"missing_query_parameter\":\"" + queryParameterName + "\"}";
    }
}
