package components.exceptions;

public class IllegalModelArgumentException extends IllegalArgumentException {
    public IllegalModelArgumentException(String message) {
        super("Invalid model name" + message);
    }
}
