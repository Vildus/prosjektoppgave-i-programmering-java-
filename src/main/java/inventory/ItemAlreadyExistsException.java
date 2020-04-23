package inventory;

public class ItemAlreadyExistsException extends Exception{

    public ItemAlreadyExistsException(int articleNumber) {
        super(String.format("Article number already excists: %d", articleNumber));
    }
}
