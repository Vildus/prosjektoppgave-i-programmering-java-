package purchase;

import components.Component;
import components.Keyboard;
import components.Mouse;
import inventory.Inventory;
import inventory.Item;
import inventory.exceptions.ItemAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import purchase.exceptions.ItemAvailableStockException;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBagTest {

    @BeforeAll
    static void setupTests() throws ItemAlreadyExistsException {
        createTestInventory();
    }

    private static void createTestInventory() throws ItemAlreadyExistsException {
        Inventory inventory = Inventory.getInstance();
        Component component1 = new Mouse("Dell", "1", "USB");
        Item item1 = new Item(component1, 120.0, 7234567);
        item1.setInStock(10);
        inventory.addItem(item1);

        Component component2 = new Keyboard("HP", "2", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        item2.setInStock(10);
        inventory.addItem(item2);
    }

    @Test
    void testGetTotalPrice() throws ItemAvailableStockException, IllegalArgumentException {
        Inventory inventory = Inventory.getInstance();
        ShoppingBag shoppingBag = ShoppingBag.getInstance();
        shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7234567), 3));
        shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7564739), 2));
        double totalPrice1 = shoppingBag.getTotalPrice();
        assertEquals(1360.0, totalPrice1);

        shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7234567), 1));
        shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7564739), 1));
        double totalPrice2 = shoppingBag.getTotalPrice();
        assertEquals(620, totalPrice2);
    }

    @Test
    void testAvailableStock() {
        Inventory inventory = Inventory.getInstance();
        ShoppingBag shoppingBag = ShoppingBag.getInstance();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7234569), 42));
                });
        assertThrows(
                ItemAvailableStockException.class,
                () -> {
                    shoppingBag.addItem(new ShoppingBagItem(inventory.findItemByArticleNumber(7234567), 42));
                });
    }
}