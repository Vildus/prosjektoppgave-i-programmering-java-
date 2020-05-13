package inventory.exceptions;

public class InvalidPriceArgumentException extends IllegalArgumentException {
    public InvalidPriceArgumentException(String message) {
        super("Invalid price: " + message);
    }
}
