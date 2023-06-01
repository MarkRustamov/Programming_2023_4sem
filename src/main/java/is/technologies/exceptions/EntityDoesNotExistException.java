package is.technologies.exceptions;

public class EntityDoesNotExistException extends Exception {
    public EntityDoesNotExistException() {}

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}
