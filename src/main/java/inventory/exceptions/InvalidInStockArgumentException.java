package inventory.exceptions;

public class InvalidInStockArgumentException extends IllegalArgumentException {
    public InvalidInStockArgumentException(String message) {
        super("Invalid in stock: " + message);
    }
}


