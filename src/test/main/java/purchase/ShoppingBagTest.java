package purchase;

import components.Component;
import components.Keyboard;
import components.Mouse;
import inventory.Inventory;
import inventory.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBagTest {
    Inventory inventory;

    public ShoppingBagTest() {
        this.inventory = this.createTestInventory();
    }


    //Legge til varer i shopping bagen. Regne ut totalpris, fjerne og justere antall?

    private Inventory createTestInventory() {
        Inventory inventory = new Inventory();
        Component component1 = new Mouse("Dell", "1", "USB");
        Item item1 = new Item(component1, 120.0, 7234567);
        item1.setInStock(10);
        inventory.addItem(item1);

        Component component2 = new Keyboard("HP", "2", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        item2.setInStock(10);
        inventory.addItem(item2);

        return inventory;
    }


    //Tester total price samt oppdatering av antall og totalprice igjen

    @Test
    void testGetTotalPrice() throws ItemAvailableStockException {
        ShoppingBag shoppingBag = new ShoppingBag(inventory);
        shoppingBag.setItem(this.inventory.findItemByArticleNumber(7234567), 3);
        shoppingBag.setItem(this.inventory.findItemByArticleNumber(7564739), 2);
        double totalPrice1 = shoppingBag.getTotalPrice();
        assertEquals(1360.0, totalPrice1);

        //Oppdaterer antall for å teste at total price oppdateres
        shoppingBag.setItem(this.inventory.findItemByArticleNumber(7234567), 1);
        shoppingBag.setItem(this.inventory.findItemByArticleNumber(7564739), 1);
        double totalPrice2 = shoppingBag.getTotalPrice();
        assertEquals(620, totalPrice2);
    }


    //teste at man legger til flere varer enn tilgjengelig og får exception som man vil ha
    @Test
    void testAvailableStock() {
        ShoppingBag shoppingBag = new ShoppingBag(inventory);
        assertThrows(
                //assertThrows tar to arguments: 1.arg = den exception man vil ha. 2.argument = lamda(metoden)
                ItemAvailableStockException.class,
                () -> {
                    shoppingBag.setItem(this.inventory.findItemByArticleNumber(7234567), 11);
                });
    }

}