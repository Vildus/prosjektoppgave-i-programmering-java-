package inventory;

public class ItemAlreadyExcistException extends Exception{

    public ItemAlreadyExcistException(int articleNumber) {
        super(String.format("Article number already excists: %d", articleNumber));
    }
}
