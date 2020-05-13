package inventory;

public class InvalidPriceArgumentException extends IllegalArgumentException {

    public InvalidPriceArgumentException(String message) {
        super("Invalid Price" + message);
    }
}
