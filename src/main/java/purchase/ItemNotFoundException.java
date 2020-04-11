package purchase;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(int articleNumber) {
        super(String.format("Article not found: %d", articleNumber));
    }
}
