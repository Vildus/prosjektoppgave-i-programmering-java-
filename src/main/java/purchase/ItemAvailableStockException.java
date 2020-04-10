package purchase;

import inventory.Inventory;
import inventory.Item;

public class ItemAvailableStockException extends Exception {

    public ItemAvailableStockException(int articleNumber, int amount, int inStock) {
        super(String.format("Selected amount: %d exceeds available: %d for item: %d", amount, inStock, articleNumber));
    }
}
