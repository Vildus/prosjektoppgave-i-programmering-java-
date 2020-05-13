package components.exceptions;

public class IllegalBrandArgumentException extends IllegalArgumentException {
    public IllegalBrandArgumentException(String message) {
        super("Invalid brand name" + message);
    }
}
