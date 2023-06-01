package is.technologies.exceptions;

public class EngineException extends Exception {
    public EngineException() {}

    public EngineException(int capacityOrHeightOrNumberOfCylinders) {
        super("Engine capacity, height and number of cylinders can't be " + capacityOrHeightOrNumberOfCylinders + ", it should be greater than zero!");
    }
}
