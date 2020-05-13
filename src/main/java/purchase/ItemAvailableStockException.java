package purchase;


public class ItemAvailableStockException extends Exception {

    public ItemAvailableStockException(int articleNumber, int amount, int inStock) {
        super(String.format("Selected amount: %d exceeds available: %d for item: %d", amount, inStock, articleNumber));
    }
}
