package inventory;

public class InvalidInStockArgumentException extends IllegalArgumentException {

    public InvalidInStockArgumentException(String message) {
        super("Invalid amount" + message);
    }
}


