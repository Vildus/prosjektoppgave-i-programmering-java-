package purchase.exceptions;

public class IllegalCustomerIDException extends IllegalArgumentException {
    public IllegalCustomerIDException(String message) {
        super(message);
    }
}
