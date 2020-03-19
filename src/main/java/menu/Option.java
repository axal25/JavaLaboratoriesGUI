package menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import utils.ExceptionMessageGenerator;

public class Option {
    private String className = null;
    private String functionName = null;
    private OptionFunctionalInterface optionFunctionalInterface = null;

    public Option() {}

    public Option( String className, String functionName, OptionFunctionalInterface optionFunctionalInterface ) {
        this.className = className;
        this.functionName = functionName;
        this.optionFunctionalInterface = optionFunctionalInterface;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public OptionFunctionalInterface getOptionFunctionalInterface() {
        return optionFunctionalInterface;
    }

    public void setOptionFunctionalInterface(OptionFunctionalInterface optionFunctionalInterface) {
        this.optionFunctionalInterface = optionFunctionalInterface;
    }

    @Override
    public String toString() {
        final String functionName = "toString()";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String stringRepresentation = null;
        try {
            stringRepresentation = objectMapper.writeValueAsString( this );
        }
        catch(Exception e) {
            stringRepresentation = ExceptionMessageGenerator.getMessage( this.className, functionName, e);
        }
        return stringRepresentation;
    }
}
