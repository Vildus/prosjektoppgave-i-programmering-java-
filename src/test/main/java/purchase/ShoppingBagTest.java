package purchase;

import components.Component;
import components.Keyboard;
import components.Mouse;
import inventory.Inventory;
import inventory.Item;
import org.junit.jupiter.api.Test;


class ShoppingBagTest {

    //Legge til varer i shopping bagen. Regne ut totalpris, fjerne og justere antall?

    //Legge til varer i inventory og så legge til i handlekurv
    private ShoppingBag createTestShoppingBag() throws ItemAvailableStockException {
        Inventory inventory = new Inventory();

        Component component1 = new Mouse("Dell", "1", "USB");
        Item item1 = new Item(component1, 120.0, 7234567);
        inventory.addItem(item1);

        Component component2 = new Keyboard("HP", "2", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        inventory.addItem(item2);
        // Ingen mulighet til å adde 2 stk. av mus med samme artikkelnummer
        //@Mikael, hva skal vi gjøre med det? Jeg prøvde en løsning - se Inventory-klassen

        ShoppingBag shoppingBag = new ShoppingBag(inventory);
        shoppingBag.setItem(item1, 1);
        shoppingBag.setItem(item2, 1);
        return shoppingBag;
    }

    //Metode for å regne ut totalprise:

    @Test
    void testGetTotalPrice() throws ItemAvailableStockException {
        ShoppingBag shoppingBag = this.createTestShoppingBag();
        shoppingBag.getTotalPrice();
    }



}