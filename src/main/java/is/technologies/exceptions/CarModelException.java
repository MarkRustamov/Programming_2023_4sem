package is.technologies.exceptions;

public class CarModelException extends Exception {
    public CarModelException() {}

    public CarModelException(String bodyType) {
        super("Invalid car body type " + bodyType);
    }

    public CarModelException(int lengthOrWidth) {
        super("Car length, width and height can't be " + lengthOrWidth + ", it should be greater than zero!");
    }
}
