package inventory.exceptions;

public class ItemAlreadyExistsException extends Exception{
    public ItemAlreadyExistsException(int articleNumber) {
        super(String.format("Article number already exists: %d", articleNumber));
    }
}
