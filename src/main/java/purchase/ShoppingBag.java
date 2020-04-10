package purchase;

import inventory.Inventory;
import inventory.Item;

import java.util.*;

public class ShoppingBag {
    //Starte med en tom shoppingbag. Når en ny bruker kommer inn lager man en ny bag for den brukeren
    //trenger bare artikkelnummer og antall
    //Når antall går til 0.. må være noe logikk som fjerner item fra shopping bag

    //Kan bare være en på en key.
    private Map<Item, Integer> items; // item er key, og value er amount
    private Inventory inventory; //tilgang til varelager

    public ShoppingBag(Inventory inventory) {
        this.items = new HashMap<>();
        this.inventory = inventory;
        //tom liste
    }

    // hvis man legger til ett art.nummer to ganger så vil vi at antallet oppdateres på den
    //varen ikke at den legges til to ganger


    //Hva om det er flere brukere som handler samtidig?
    //Denne logikken kan vi ta senere. Eller skal vi tillate kun en bruker som shopper samtidig
    // da slipper vi flere shoppingbag instanser og problem med varebeholdning som vi må skrive logikk for.
    // Da må vi skrive dette i oppgaven: Dette programmet tillater kun en som handler om gangen


    //TODO:
    //skal oppdatere antallet
    //1; finne ut om varen finnes
    //2: finne ut om det er nok instock
    //3: oppdatere antall


    public void setItem(Item item, int amount) throws ItemAvailableStockException {
        int inStock = item.getInStock();
        if (inStock < amount) {
            throw new ItemAvailableStockException(item.getArticleNumber(), amount, inStock);
        }
        this.items.put(item, amount);
    }


    public void removeItem(Item item) {
        this.items.remove(item);
    }

    //HashMap har ikke index. Ketvalue = index.
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Item, Integer> entry : this.items.entrySet()) {
            Item item = entry.getKey();
            int amount = entry.getValue();
            totalPrice = totalPrice + item.getPrice() * amount;
        }
        return totalPrice;
    }
}
